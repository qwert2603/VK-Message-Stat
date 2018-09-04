package com.qwert2603.vkmessagestat.results

import android.view.ViewGroup
import com.qwert2603.vkmessagestat.R
import com.qwert2603.vkmessagestat.base.recyclerview.BaseRecyclerViewAdapter
import com.qwert2603.vkmessagestat.inflate
import com.qwert2603.vkmessagestat.model.OneResult
import com.qwert2603.vkmessagestat.oneresult.OneResultViewHolder

class ResultsAdapter : BaseRecyclerViewAdapter<OneResult, OneResultViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OneResultViewHolder
            = OneResultViewHolder(parent.inflate(R.layout.item_one_result), this)

}