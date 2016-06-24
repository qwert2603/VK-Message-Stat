package com.qwert2603.vkmessagestat.model

import com.qwert2603.vkmessagestat.results.IntervalType
import rx.Observable

interface DataManager {
    fun getMessageStatistic(intervalType: IntervalType, value: Int): Observable<Results>
}