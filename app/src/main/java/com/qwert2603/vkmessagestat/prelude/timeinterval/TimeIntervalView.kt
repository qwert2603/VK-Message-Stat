package com.qwert2603.vkmessagestat.prelude.timeinterval

import android.support.annotation.DrawableRes

import com.qwert2603.vkmessagestat.base.BaseView
import com.qwert2603.vkmessagestat.model.TimeInterval

interface TimeIntervalView : BaseView {
    fun showTimeInterval(interval: TimeInterval)
    fun showIntervalImage(@DrawableRes drawableRes: Int)
}
