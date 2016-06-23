package com.qwert2603.vkmessagestat.results

import com.qwert2603.vkmessagestat.model.OneResult

data class ResultsModel(
        val done: Int,
        val total: Int,
        val resultsList: List<OneResult> = emptyList()
)