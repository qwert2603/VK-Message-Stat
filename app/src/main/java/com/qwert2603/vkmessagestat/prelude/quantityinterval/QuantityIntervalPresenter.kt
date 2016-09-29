package com.qwert2603.vkmessagestat.prelude.quantityinterval

import com.qwert2603.vkmessagestat.R
import com.qwert2603.vkmessagestat.base.BasePresenter
import com.qwert2603.vkmessagestat.model.QuantityInterval

class QuantityIntervalPresenter : BasePresenter<QuantityInterval, QuantityIntervalView>() {

    fun setInterval(interval: QuantityInterval) {
        model = interval
    }

    override fun onUpdateView(view: QuantityIntervalView) {
        val interval = model
        if (interval != null) {
            view.showIntervalImage(R.drawable.last)
            view.showQuantityInterval(interval)
        }
    }
}
