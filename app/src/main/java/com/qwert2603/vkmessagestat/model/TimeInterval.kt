package com.qwert2603.vkmessagestat.model

data class TimeInterval(val interval: Int) : Identifiable {
    override fun getId() = interval
}
