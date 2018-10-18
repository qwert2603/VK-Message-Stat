package com.qwert2603.vkmessagestat.oneresult

import com.qwert2603.vkmessagestat.base.BasePresenter
import com.qwert2603.vkmessagestat.model.OneResult

open class OneResultPresenter : BasePresenter<OneResult, OneResultView>() {

    fun setOneResult(oneResult: OneResult) {
        model = oneResult
    }

    override fun onUpdateView(view: OneResultView) {
        if (model != null) {
            view.setCanClick(model.resultInfo.canClick())
            view.showName(model.resultInfo.name)
            view.showPhoto(model.resultInfo.photoUrl)
            view.showPercent(model.percent)
            view.showQuantity(model.quantity)
        }
    }

}
