package com.qwert2603.vkmessagestat.mock

import android.support.v4.util.Pair
import com.qwert2603.vkmessagestat.model.DataManager
import com.qwert2603.vkmessagestat.model.Results
import com.qwert2603.vkmessagestat.results.IntervalType
import com.qwert2603.vkmessagestat.vkapihelper.Progress
import rx.Observable

class MockDataManager : DataManager {
    override fun getMessageStatistic(intervalType: IntervalType, value: Int): Pair<Observable<Results>, Observable<Progress>> {
        throw UnsupportedOperationException()
    }

    override fun logOut() {
        throw UnsupportedOperationException()
    }
}
