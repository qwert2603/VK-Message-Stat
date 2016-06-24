package com.qwert2603.vkmessagestat.mock

import com.qwert2603.vkmessagestat.model.DataManager
import com.qwert2603.vkmessagestat.model.Results
import com.qwert2603.vkmessagestat.results.IntervalType
import rx.Observable

class MockDataManager : DataManager {
    override fun getMessageStatistic(intervalType: IntervalType, value: Int): Observable<Results> {
        throw UnsupportedOperationException()
    }
}
