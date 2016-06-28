package com.qwert2603.vkmessagestat.model

import android.support.v4.util.Pair
import com.qwert2603.vkmessagestat.results.IntervalType
import com.qwert2603.vkmessagestat.vkapihelper.Progress
import rx.Observable

interface DataManager {
    fun getMessageStatistic(intervalType: IntervalType, value: Int): Pair<Observable<Results>, Observable<Progress>>
    fun logOut()
}