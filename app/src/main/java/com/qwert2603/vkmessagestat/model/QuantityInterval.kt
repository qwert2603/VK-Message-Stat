package com.qwert2603.vkmessagestat.model

data class QuantityInterval(val interval: Int) : Identifiable {
    override fun getId() = interval
}
