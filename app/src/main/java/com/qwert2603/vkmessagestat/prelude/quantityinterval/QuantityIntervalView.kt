package com.qwert2603.vkmessagestat.prelude.quantityinterval

import android.support.annotation.DrawableRes

import com.qwert2603.vkmessagestat.base.BaseView
import com.qwert2603.vkmessagestat.model.QuantityInterval

interface QuantityIntervalView : BaseView {
    fun showQuantityInterval(interval: QuantityInterval)
    fun showIntervalImage(@DrawableRes drawableRes: Int)
}
