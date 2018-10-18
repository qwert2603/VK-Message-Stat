package com.qwert2603.vkmessagestat.model

import com.qwert2603.vkmessagestat.Const
import com.qwert2603.vkmessagestat.VkMessageStatApplication
import com.qwert2603.vkmessagestat.results.IntervalType
import com.qwert2603.vkmessagestat.vkapihelper.Progress
import com.qwert2603.vkmessagestat.vkapihelper.VkApiHelper
import rx.Observable
import rx.Scheduler
import javax.inject.Inject
import javax.inject.Named

class DataManagerImpl : DataManager {

    @Inject
    lateinit var mVkApiHelper: VkApiHelper

    @Inject
    @field:[Named(Const.IO_THREAD)]
    lateinit var mIOScheduler: Scheduler

    @Inject
    @field:[Named(Const.UI_THREAD)]
    lateinit var mUIScheduler: Scheduler

    init {
        VkMessageStatApplication.getAppComponent().inject(this@DataManagerImpl)
    }

    override fun getMessageStatistic(intervalType: IntervalType, value: Int): Pair<Observable<Results>, Observable<Progress>> {
        val messageStatistic = mVkApiHelper.getMessageStatistic(intervalType, value)
        val stats = messageStatistic.first.cache()
        return Pair(
                Observable.zip(stats, stats.flatMap { this.getOneResults(it) }
                ) { map, listObservable -> Results(intervalType, value, map.totalSum, map.totalSum, listObservable) }
                        .onBackpressureBuffer()
                        .subscribeOn(mIOScheduler)
                        .observeOn(mUIScheduler),
                messageStatistic.second
                        .onBackpressureBuffer()
                        .subscribeOn(mIOScheduler)
                        .observeOn(mUIScheduler)
        )
    }

    override fun logOut() {
        mVkApiHelper.logOut()
    }

    private fun getOneResults(map: IntegerCountMap): Observable<List<OneResult>> =
            mVkApiHelper.getResultInfos(map.keys.toList())
                    .map { resultsList ->
                        resultsList
                                .map {
                                    OneResult(
                                            resultInfo = it,
                                            percent = map.getPercent(it.id.toInt()),
                                            quantity = map.getCount(it.id.toInt())
                                    )
                                }
                                .sortedByDescending { it.quantity }
                    }

}
