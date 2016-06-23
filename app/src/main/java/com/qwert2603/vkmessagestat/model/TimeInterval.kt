package com.qwert2603.vkmessagestat.model

data class TimeInterval(var interval: Int) : Identifiable {
    override fun getId() = interval
}
