package com.qwert2603.vkmessagestat.one_result

import com.qwert2603.vkmessagestat.base.BasePresenter
import com.qwert2603.vkmessagestat.getPhoto
import com.qwert2603.vkmessagestat.model.OneResult

class OneResultPresenter : BasePresenter<OneResult, OneResultView>() {

    fun setOneResult(oneResult: OneResult) {
        model = oneResult
    }

    override fun onUpdateView(view: OneResultView) {
        if (model != null) {
            view.showName(model.user.first_name + " " + model.user.last_name)
            view.showPhoto(model.user.getPhoto())
            view.showPercent(model.percent)
            view.showQuantity(model.quantity)
        }
    }

}
