package com.qwert2603.vkmessagestat.model

import android.content.Context
import com.qwert2603.vkmessagestat.R

data class QuantityInterval(val interval: Int) : Identifiable {
    override fun getId() = interval

    fun toString(context: Context) : String {
        var string = context.resources.getQuantityString(R.plurals.quantities, interval, interval)
        if (interval <= 0) {
            string = string.replace("-1", context.getString(R.string.all))
        }
        return string
    }
}
