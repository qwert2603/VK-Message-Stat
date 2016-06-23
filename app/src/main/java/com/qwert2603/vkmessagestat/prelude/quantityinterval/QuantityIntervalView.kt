package com.qwert2603.vkmessagestat.prelude.quantityinterval

import android.support.annotation.DrawableRes

import com.qwert2603.vkmessagestat.base.BaseView

interface QuantityIntervalView : BaseView {
    fun showQuantityInterval(quantity: Int)
    fun showIntervalImage(@DrawableRes drawableRes: Int)
}
