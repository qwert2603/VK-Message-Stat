package com.qwert2603.vkmessagestat.oneresult

import com.qwert2603.vkmessagestat.base.BaseView

interface OneResultView : BaseView {
    fun showPhoto(url: String)
    fun showName(name: String)
    fun showPercent(percent: Double)
    fun showQuantity(quantity: Int)
}