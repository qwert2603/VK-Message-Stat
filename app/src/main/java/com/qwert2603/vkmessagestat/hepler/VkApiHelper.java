package com.qwert2603.vkmessagestat.hepler;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

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
import com.vk.sdk.api.model.VKUsersArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

import rx.Observable;

public class VkApiHelper {

    /**
     * Задержка перед следующим запросом.
     * Чтобы запросы не посылались слишком часто. (Не больше 3 в секунду).
     */
    public final long nextRequestDelay = 400;

    /**
     * Время, когда можно посылать следующий запрос.
     */
    private long nextRequestTime;

    {
        nextRequestTime = SystemClock.uptimeMillis();
    }

    /**
     * Обрботчик отправки запросов
     */
    private final Handler mHandler;

    {
        mHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * Отправить запрос.
     * Запросы выполняются последовательно.
     * Переданный запрос будет отправлен когда придет его время.
     */
    public synchronized void sendRequest(VKRequest request, VKRequest.VKRequestListener listener) {
        if (!VKSdk.isLoggedIn()) {
            return;
        }
        if (nextRequestTime <= SystemClock.uptimeMillis()) {
            request.executeWithListener(listener);
            nextRequestTime = SystemClock.uptimeMillis();
        } else {
            mHandler.postAtTime(() -> {
                if (VKSdk.isLoggedIn()) {
                    request.executeWithListener(listener);
                }
            }, nextRequestTime);
        }
        nextRequestTime += nextRequestDelay;
    }

    public void logOut() {
        if (VKSdk.isLoggedIn()) {
            VKSdk.logout();
        }
    }

    private static final int USERS_PER_REQUEST = 1000;
    private static final int FRIENDS_PER_REQUEST = 5000;
    private static final int MESSAGES_PER_REQUEST = 100;

    public Observable<IntegerCountMap> getMessageStatistic(IntervalType intervalType, int value) {
        int minTime = Integer.MAX_VALUE;
        switch (intervalType) {
            case QUANTITY:
                minTime = Integer.MIN_VALUE;
                break;
            case TIME:
                minTime = value != -1 ? (int) (System.currentTimeMillis() / 1000) : Integer.MIN_VALUE;
        }
        final int finalMinTime = minTime;
        return getLastMessageId()
                .flatMap(lastId -> getStarts(lastId, value))
                .flatMap(start -> getMessageStatistic(start, finalMinTime))
                .takeWhile(IntegerCountMap::notEmpty)
                .reduce(IntegerCountMap::addAll);
    }

    private Observable<Integer> getStarts(int lastId, int value) {
        return Observable.range(0, (value - 1) / MESSAGES_PER_REQUEST + 1)
                .map(i -> lastId - i * MESSAGES_PER_REQUEST);
    }

    private Observable<IntegerCountMap> getMessageStatistic(int start, int minTime) {
        return Observable.create(subscriber -> {
            VKParameters parameters = VKParameters.from("start", start);
            VKRequest vkRequest = new VKRequest("execute.getStat", parameters);
            vkRequest.setUseLooperForCallListener(false);
            sendRequest(vkRequest, new VKRequest.VKRequestListener() {
                @Override
                public void onComplete(VKResponse response) {
                    try {
                        JSONObject response1 = response.json.getJSONObject("response");
                        JSONArray ids = response1.getJSONArray("ids");
                        JSONArray time = response1.getJSONArray("time");
                        int length = ids.length();
                        IntegerCountMap map = new IntegerCountMap();
                        for (int i = 0; i < length; i++) {
                            if (time.getInt(i) < minTime) {
                                break;
                            }
                            map.add(ids.getInt(i), 1);
                        }
                        subscriber.onNext(map);
                        subscriber.onCompleted();
                    } catch (JSONException e) {
                        subscriber.onError(new RuntimeException(e.toString()));
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
     * @return id последнего сообщения ВК.
     */
    private Observable<Integer> getLastMessageId() {
        return Observable.create(subscriber -> {
            VKRequest vkRequest = new VKRequest("execute.lastId");
            vkRequest.setUseLooperForCallListener(false);
            sendRequest(vkRequest, new VKRequest.VKRequestListener() {
                @Override
                public void onComplete(VKResponse response) {
                    subscriber.onNext(Integer.parseInt(response.responseString));
                    subscriber.onCompleted();
                }

                @Override
                public void onError(VKError error) {
                    subscriber.onError(new RuntimeException(error.toString()));
                }
            });
        });
    }

    public Observable<List<VKApiUserFull>> getFriends() {
        Observable<List<VKApiUserFull>> firstFriends = getFriends(FRIENDS_PER_REQUEST, 0).cache();
        return firstFriends.map(friends -> ((VKUsersArray) friends).getCount())
                .flatMap(i -> Observable.range(0, (i - 1) / FRIENDS_PER_REQUEST + 1))
                .flatMap(i -> i == 0 ? firstFriends : getFriends(FRIENDS_PER_REQUEST, i * FRIENDS_PER_REQUEST))
                .flatMap(Observable::from)
                .toList();
    }

    public Observable<List<VKApiUserFull>> getFriends(int count, int offset) {
        return Observable.create(subscriber -> {
            VKParameters vkParameters = VKParameters.from(
                    VKApiConst.FIELDS, "photo_200, can_write_private_message",
                    "order", "hints",
                    VKApiConst.COUNT, String.valueOf(count),
                    VKApiConst.OFFSET, String.valueOf(offset)
            );
            VKRequest request = VKApi.friends().get(vkParameters);
            request.setUseLooperForCallListener(false);
            sendRequest(request, new VKRequest.VKRequestListener() {
                @Override
                public void onComplete(VKResponse response) {
                    subscriber.onNext((VKUsersArray) response.parsedModel);
                    subscriber.onCompleted();
                }

                @Override
                public void onError(VKError error) {
                    subscriber.onError(new RuntimeException(error.toString()));
                }
            });
        });
    }

    public Observable<VKApiUserFull> getUserById(int userId) {
        return getUsersById(Collections.singletonList(userId))
                .flatMap(Observable::from)
                .first();
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

    /**
     * @return пользователь, который использует приложение.
     */
    public Observable<VKApiUserFull> getMyself() {
        VKParameters vkParameters = VKParameters.from(VKApiConst.FIELDS, "photo_200");
        return getUsers(vkParameters)
                .flatMap(Observable::from)
                .first();
    }

    private Observable<List<VKApiUserFull>> getUsers(VKParameters vkParameters) {
        return Observable.create(subscriber -> {
            VKRequest request = VKApi.users().get(vkParameters);
            request.setUseLooperForCallListener(false);
            sendRequest(request, new VKRequest.VKRequestListener() {
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

    /**
     * Исключение -- ошибка отправки сообщения
     */
    public static class SendMessageException extends Exception {
        /**
         * Объект для идентификации сообщения, которое не удалось отправить.
         */
        public Object mToken;

        public SendMessageException(String detailMessage, Object token) {
            super(detailMessage);
            mToken = token;
        }
    }

    /**
     * Отправить сообщение.
     *
     * @param token объект, который будет передаст Observable, который вернет метод.
     *              (для идентификации сообщения и адресата).
     *              При ошибке будет передано исключение SendMessageException.
     *              mToken этого иключения будет содержать переданный методу @param token.
     */
    public Observable<Object> sendMessage(int userId, String message, Object token) {
        return Observable
                .create(subscriber -> {
                    VKParameters parameters = VKParameters.from(
                            VKApiConst.USER_ID, userId, VKApiConst.MESSAGE, message);
                    VKRequest request = new VKRequest("messages.send", parameters);
                    request.setUseLooperForCallListener(false);
                    sendRequest(request, new VKRequest.VKRequestListener() {
                        @Override
                        public void onComplete(VKResponse response) {
                            subscriber.onNext(token);
                            subscriber.onCompleted();
                        }

                        @Override
                        public void onError(VKError error) {
                            subscriber.onError(new SendMessageException(error.toString(), token));
                        }
                    });
                });
    }

}
