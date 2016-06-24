package com.qwert2603.vkmessagestat.model

import com.qwert2603.vkmessagestat.results.IntervalType

data class Results(
        val intervalType: IntervalType,
        val value: Int,
        val done: Int,
        val total: Int,
        val resultsList: List<OneResult>
)