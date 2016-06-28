package com.qwert2603.vkmessagestat.model;

import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

import com.qwert2603.vkmessagestat.Const;
import com.qwert2603.vkmessagestat.VkMessageStatApplication;
import com.qwert2603.vkmessagestat.results.IntervalType;
import com.qwert2603.vkmessagestat.vkapihelper.Progress;
import com.qwert2603.vkmessagestat.vkapihelper.VkApiHelper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

public class DataManagerImpl implements DataManager {

    @Inject
    VkApiHelper mVkApiHelper;

    @Inject
    @Named(Const.IO_THREAD)
    Scheduler mIOScheduler;

    @Inject
    @Named(Const.UI_THREAD)
    Scheduler mUIScheduler;

    public DataManagerImpl() {
        VkMessageStatApplication.getAppComponent().inject(DataManagerImpl.this);
    }

    @NonNull
    @Override
    public Pair<Observable<Results>, Observable<Progress>> getMessageStatistic(@NonNull IntervalType intervalType, int value) {
        Pair<Observable<IntegerCountMap>, Observable<Progress>> messageStatistic =
                mVkApiHelper.getMessageStatistic(intervalType, value);
        Observable<IntegerCountMap> stats = messageStatistic.first.cache();
        return new Pair<>(
                Observable.zip(stats, stats.flatMap(this::getOneResults),
                        (map, listObservable) ->
                                new Results(intervalType, value, map.getTotalSum(), map.getTotalSum(), listObservable))
                        .onBackpressureBuffer()
                        .subscribeOn(mIOScheduler)
                        .observeOn(mUIScheduler),
                messageStatistic.second
                        .onBackpressureBuffer()
                        .subscribeOn(mIOScheduler)
                        .observeOn(mUIScheduler)
        );
    }

    @Override
    public void logOut() {
        mVkApiHelper.logOut();
    }

    private Observable<List<OneResult>> getOneResults(IntegerCountMap map) {
        return mVkApiHelper.getUsersById(map.keysAsList())
                .flatMap(Observable::from)
                .map(user -> new OneResult(user, map.getPercent(user.id), map.get(user.id)))
                .toSortedList((r1, r2) -> Integer.compare(r2.getQuantity(), r1.getQuantity()));
    }

}
