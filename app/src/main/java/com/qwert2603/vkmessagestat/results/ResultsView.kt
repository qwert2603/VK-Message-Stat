package com.qwert2603.vkmessagestat.results

import com.qwert2603.vkmessagestat.base.BaseView
import com.qwert2603.vkmessagestat.model.OneResult

interface ResultsView : BaseView {
    fun showTitle(title: String)
    fun showLayer(layer: Layer)
    fun showProgress(process: Int)
    fun showTotal(total: String)
    fun showDone(done: Int)
    fun showResultList(oneResults: List<OneResult>)
    fun showNoInternet()
    fun moveToPage(vkId: Int)
}