package com.qwert2603.vkmessagestat.prelude.quantityinterval

import android.view.LayoutInflater
import android.view.ViewGroup
import com.qwert2603.vkmessagestat.R
import com.qwert2603.vkmessagestat.base.recyclerview.BaseRecyclerViewAdapter
import com.qwert2603.vkmessagestat.model.QuantityInterval

class QuantityIntervalAdapter : BaseRecyclerViewAdapter<QuantityInterval, QuantityIntervalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuantityIntervalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_interval, parent, false)
        return QuantityIntervalViewHolder(view, this@QuantityIntervalAdapter)
    }

}
