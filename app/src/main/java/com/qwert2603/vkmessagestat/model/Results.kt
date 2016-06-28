package com.qwert2603.vkmessagestat.model

import com.qwert2603.vkmessagestat.results.IntervalType

data class Results(
        val intervalType: IntervalType,
        val value: Int,
        var done: Int,
        var total: Int,
        val resultsList: List<OneResult>
) {
    fun progress() = if (total == 0) 0 else (done * 100 / total)

    fun interval(): Interval {
        when (intervalType) {
            IntervalType.TIME -> return TimeInterval(value)
            IntervalType.QUANTITY -> return QuantityInterval(if (value == -1) total else value)
        }
    }
}