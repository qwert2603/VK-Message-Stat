package com.qwert2603.vkmessagestat.model

import android.content.Context
import com.qwert2603.vkmessagestat.R

data class TimeInterval(val interval: Int) : Interval, Identifiable {
    override fun getId() = interval

    override fun toString(context: Context): String = context.resources.getQuantityString(R.plurals.hours, interval, interval)
}
