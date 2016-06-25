package com.qwert2603.vkmessagestat.model

import android.content.Context
import com.qwert2603.vkmessagestat.R

data class TimeInterval(val interval: Int) : Identifiable {
    override fun getId() = interval

    fun toString(context: Context) = context.resources.getQuantityString(R.plurals.hours, interval, interval)
}
