package com.qwert2603.vkmessagestat.prelude.timeinterval

import com.qwert2603.vkmessagestat.R
import com.qwert2603.vkmessagestat.base.BasePresenter
import com.qwert2603.vkmessagestat.model.TimeInterval

class TimeIntervalPresenter : BasePresenter<TimeInterval, TimeIntervalView>() {

    fun setInterval(interval: TimeInterval) {
        model = interval
    }

    override fun onUpdateView(view: TimeIntervalView) {
        val interval = model
        if (interval != null) {
            view.showIntervalImage(R.drawable.hours)
            view.showTimeInterval(interval)
        }
    }
}
