package com.qwert2603.vkmessagestat.prelude.timeinterval

import android.view.LayoutInflater
import android.view.ViewGroup
import com.qwert2603.vkmessagestat.R
import com.qwert2603.vkmessagestat.base.recyclerview.BaseRecyclerViewAdapter
import com.qwert2603.vkmessagestat.model.TimeInterval

class TimeIntervalAdapter : BaseRecyclerViewAdapter<TimeInterval, TimeIntervalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeIntervalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_interval, parent, false)
        return TimeIntervalViewHolder(view, this@TimeIntervalAdapter)
    }

}
