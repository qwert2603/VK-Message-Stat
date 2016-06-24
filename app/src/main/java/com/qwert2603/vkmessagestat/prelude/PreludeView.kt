package com.qwert2603.vkmessagestat.prelude

import com.qwert2603.vkmessagestat.base.BaseView
import com.qwert2603.vkmessagestat.model.QuantityInterval
import com.qwert2603.vkmessagestat.model.TimeInterval
import com.qwert2603.vkmessagestat.results.IntervalType

interface PreludeView : BaseView {
    fun showTimes(times: List<TimeInterval>)
    fun showQuantities(quantities: List<QuantityInterval>)
    fun moveToResults(intervalType: IntervalType, value: Int)
}
