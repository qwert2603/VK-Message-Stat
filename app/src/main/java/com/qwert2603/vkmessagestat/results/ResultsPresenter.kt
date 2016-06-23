package com.qwert2603.vkmessagestat.results

import com.qwert2603.vkmessagestat.base.BasePresenter

class ResultsPresenter : BasePresenter<ResultsModel, ResultsView>() {
    override fun onUpdateView(view: ResultsView) {
        if (model != null) {
            if (model.done < model.total) {
                view.showLayer(Layer.CALCULATING)
                view.showDone(model.done)
                view.showTotal(model.total)
                view.showProgress(model.done * 100 / model.total)
            } else {
                view.showLayer(Layer.RESULTS)
                view.showResultList(model.resultsList)
            }
        }
    }

}
