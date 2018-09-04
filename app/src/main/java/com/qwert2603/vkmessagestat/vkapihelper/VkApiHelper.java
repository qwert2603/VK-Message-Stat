package com.qwert2603.vkmessagestat.vkapihelper;

import com.qwert2603.vkmessagestat.Const;
import com.qwert2603.vkmessagestat.VkMessageStatApplication;
import com.qwert2603.vkmessagestat.model.IntegerCountMap;
import com.qwert2603.vkmessagestat.results.IntervalType;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import kotlin.Pair;
import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;

public class VkApiHelper {

    @Inject
    VkRequestSender mVkRequestSender;

    public VkApiHelper() {
        VkMessageStatApplication.Companion.getAppComponent().inject(VkApiHelper.this);
    }

    public void logOut() {
        if (VKSdk.isLoggedIn()) {
            VKSdk.logout();
        }
    }

    private static final int USERS_PER_REQUEST = 1000;
    private static final int MESSAGES_PER_ARG = 100;
    private static final int ARGS_PER_REQUEST = 25;
    private static final int MESSAGES_PER_REQUEST = MESSAGES_PER_ARG * ARGS_PER_REQUEST;

    public Pair<Observable<IntegerCountMap>, Observable<Progress>> getMessageStatistic(IntervalType intervalType, int value) {
        SerializedSubject<Progress, Progress> subject = new SerializedSubject<>(PublishSubject.create());
        Observable<IntegerCountMap> observable;

        if (intervalType == IntervalType.QUANTITY) {
            int[] progress = {0, value};
            observable = getLastMessageIdAndTime()
                    .first()
                    .doOnNext(q -> {
                        if (value == -1) {
                            progress[1] = q.getLastMessageId();
                        }
                    })
                    .flatMap(q -> getStartsInfos(q.getLastMessageId(), value != -1 ? value : q.getLastMessageId()))
                    .flatMap(startInfo -> doGetMessageStatistic(startInfo, 0))
                    .defaultIfEmpty(new Stats(new IntegerCountMap(), Integer.MAX_VALUE))
                    .map(Stats::getStatsMap)
                    .doOnNext(map -> {
                        progress[0] += map.getTotalSum();
                        if (progress[0] > progress[1]) {
                            progress[0] = progress[1];
                        }
                        subject.onNext(new Progress(progress[0], progress[1]));
                    })
                    .reduce(IntegerCountMap::addAll);
        } else {
            observable = getLastMessageIdAndTime()
                    .flatMap(q -> Observable.interval(VkRequestSender.NEXT_REQUEST_DELAY - 50, TimeUnit.MILLISECONDS)
                            .onBackpressureBuffer()
                            .zipWith(getStartsInfos(q.getLastMessageId(), q.getLastMessageId()), (l, s) -> s)
                            .concatMap(startInfo -> doGetMessageStatistic(startInfo, q.getTime() - value * Const.SECONDS_PER_HOUR))
                            .takeWhile(stats -> stats.getTime() > (q.getTime() - value * Const.SECONDS_PER_HOUR))
                            .defaultIfEmpty(new Stats(new IntegerCountMap(), Integer.MAX_VALUE))
                            .doOnNext(stats -> {
                                int hoursDone = (q.getTime() - stats.getTime()) / Const.SECONDS_PER_HOUR;
                                if (hoursDone < 0) {
                                    hoursDone = 0;
                                }
                                if (hoursDone > value) {
                                    hoursDone = value;
                                }
                                subject.onNext(new Progress(hoursDone, value));
                            }))
                    .map(Stats::getStatsMap)
                    .reduce(IntegerCountMap::addAll);
        }
        return new Pair<>(observable, subject);
    }

    private Observable<StartInfo> getStartsInfos(int lastId, int value) {
        if (value == 0) {
            return Observable.just(new StartInfo(lastId, 1, 0));
        }
        int requestsCount = (value - 1) / MESSAGES_PER_REQUEST + 1;
        int messagesInLastArg = (value - (requestsCount - 1) * MESSAGES_PER_REQUEST) % MESSAGES_PER_ARG;
        messagesInLastArg = messagesInLastArg == 0 ? MESSAGES_PER_ARG : messagesInLastArg;
        final int finalMessagesInLastArg = messagesInLastArg;
        return Observable.range(0, requestsCount)
                .map(i -> new StartInfo(
                        lastId - i * MESSAGES_PER_REQUEST,
                        Math.min(ARGS_PER_REQUEST, (value - i * MESSAGES_PER_REQUEST - 1) / MESSAGES_PER_ARG + 1),
                        i != requestsCount - 1 ? MESSAGES_PER_ARG : finalMessagesInLastArg
                ));
    }

    private Observable<Stats> doGetMessageStatistic(StartInfo startInfo, int minTime) {
        return Observable
                .<Stats>create(subscriber -> {
                    VKRequest vkRequest = new VKRequest("execute.getStat", createVkParams(startInfo));
                    vkRequest.setUseLooperForCallListener(false);
                    mVkRequestSender.sendRequest(vkRequest, new VKRequest.VKRequestListener() {
                        @Override
                        public void onComplete(VKResponse response) {
                            if (subscriber.isUnsubscribed()) {
                                return;
                            }
                            try {
                                JSONArray jsonArray = response.json.getJSONArray("response");
                                int length1 = jsonArray.length();
                                for (int j = 0; j < length1; j++) {
                                    JSONArray jsonArray1 = jsonArray.getJSONArray(j);
                                    JSONArray ids = jsonArray1.getJSONObject(0).getJSONArray("ids");
                                    JSONArray time = jsonArray1.getJSONObject(1).getJSONArray("time");
                                    int length = ids.length();
                                    if (length > 0) {
                                        IntegerCountMap map = new IntegerCountMap();
                                        for (int i = 0; i < length; i++) {
                                            if (time.getInt(i) < minTime) {
                                                break;
                                            }
                                            map.add(ids.getInt(i), 1);
                                        }
                                        if (!subscriber.isUnsubscribed()) {
                                            subscriber.onNext(new Stats(map, time.getInt(0)));
                                        }
                                    }
                                }
                                subscriber.onCompleted();
                            } catch (JSONException e) {
                                subscriber.onError(e);
                            }
                        }

                        @Override
                        public void onError(VKError error) {
                            subscriber.onError(new RuntimeException(error.toString()));
                        }
                    });
                })
                .retry(3);
    }

    private VKParameters createVkParams(StartInfo startInfo) {
        Object[] args = new Object[ARGS_PER_REQUEST * 2];
        for (int i = 0; i < ARGS_PER_REQUEST; i++) {
            args[i * 2] = "ids" + (i + 1);
            if (i < startInfo.getArgsCount()) {
                if (i == startInfo.getArgsCount() - 1) {
                    args[i * 2 + 1] = getIdsString(startInfo.getStart() - i * MESSAGES_PER_ARG, startInfo.getMessagesPerLastArg());
                } else {
                    args[i * 2 + 1] = getIdsString(startInfo.getStart() - i * MESSAGES_PER_ARG, MESSAGES_PER_ARG);
                }
            } else {
                args[i * 2 + 1] = "";
            }
        }
        return VKParameters.from(args);
    }

    private String getIdsString(int start, int count) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            stringBuilder.append(start).append(",");
            --start;
        }
        return stringBuilder.toString();
    }

    private Observable<LastMessageIdAndTime> getLastMessageIdAndTime() {
        return Observable.create(subscriber -> {
            VKRequest vkRequest = new VKRequest("execute.lastIdAndTime");
            vkRequest.setUseLooperForCallListener(false);
            mVkRequestSender.sendRequest(vkRequest, new VKRequest.VKRequestListener() {
                @Override
                public void onComplete(VKResponse response) {
                    try {
                        JSONObject jsonObject = response.json.getJSONObject("response");
                        int lastId = jsonObject.getInt("lastId");
                        int time = jsonObject.getInt("time");
                        subscriber.onNext(new LastMessageIdAndTime(lastId, time));
                        subscriber.onCompleted();
                    } catch (JSONException e) {
                        subscriber.onError(e);
                    }
                }

                @Override
                public void onError(VKError error) {
                    subscriber.onError(new RuntimeException(error.toString()));
                }
            });
        });
    }

    /**
     * @param userIdList список id пользователей.
     * @return объекты пользователей.
     */
    public Observable<List<VKApiUserFull>> getUsersById(List<Integer> userIdList) {
        return Observable.range(0, (userIdList.size() - 1) / USERS_PER_REQUEST + 1)
                .map(i -> {
                    StringBuilder stringBuilder = new StringBuilder();
                    int b = i * USERS_PER_REQUEST;
                    int e = Math.min((i + 1) * USERS_PER_REQUEST, userIdList.size());
                    for (int j = b; j < e; ++j) {
                        stringBuilder.append(userIdList.get(j)).append(",");
                    }
                    return stringBuilder.toString();
                })
                .map(idsString -> VKParameters.from(VKApiConst.USER_IDS, idsString, VKApiConst.FIELDS, "photo_200"))
                .flatMap(this::getUsers)
                .flatMap(Observable::from)
                .toList();
    }

    private Observable<List<VKApiUserFull>> getUsers(VKParameters vkParameters) {
        return Observable.create(subscriber -> {
            VKRequest request = VKApi.users().get(vkParameters);
            request.setUseLooperForCallListener(false);
            mVkRequestSender.sendRequest(request, new VKRequest.VKRequestListener() {
                @Override
                @SuppressWarnings("unchecked")
                public void onComplete(VKResponse response) {
                    subscriber.onNext((VKList<VKApiUserFull>) response.parsedModel);
                    subscriber.onCompleted();
                }

                @Override
                public void onError(VKError error) {
                    subscriber.onError(new RuntimeException(error.toString()));
                }
            });
        });
    }

}
