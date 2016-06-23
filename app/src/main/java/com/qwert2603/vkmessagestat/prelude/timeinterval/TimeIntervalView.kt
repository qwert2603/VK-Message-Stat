package com.qwert2603.vkmessagestat.prelude.timeinterval

import android.support.annotation.DrawableRes

import com.qwert2603.vkmessagestat.base.BaseView

interface TimeIntervalView : BaseView {
    fun showTimeInterval(hours: Int)
    fun showIntervalImage(@DrawableRes drawableRes: Int)
}
