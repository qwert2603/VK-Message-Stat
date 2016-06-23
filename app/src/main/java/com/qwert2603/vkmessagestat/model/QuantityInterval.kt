package com.qwert2603.vkmessagestat.model

data class QuantityInterval(var interval: Int) : Identifiable {
    override fun getId() = interval
}
