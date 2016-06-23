package com.qwert2603.vkmessagestat.results

import android.view.View
import android.view.ViewGroup
import com.qwert2603.vkmessagestat.R
import com.qwert2603.vkmessagestat.base.BaseRecyclerViewAdapter
import com.qwert2603.vkmessagestat.inflate
import com.qwert2603.vkmessagestat.loadPhoto
import com.qwert2603.vkmessagestat.model.OneResult
import com.qwert2603.vkmessagestat.one_result.OneResultPresenter
import com.qwert2603.vkmessagestat.one_result.OneResultView
import kotlinx.android.synthetic.main.item_one_result.view.*
import javax.inject.Inject

class ResultsAdapter : BaseRecyclerViewAdapter<OneResult, ResultsAdapter.OneResultViewHolder, OneResultPresenter>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): OneResultViewHolder
            = OneResultViewHolder(parent?.inflate(R.layout.item_one_result))

    class OneResultViewHolder(itemView: View?) :
            BaseRecyclerViewAdapter<OneResult, *, OneResultPresenter>.RecyclerViewHolder(itemView), OneResultView {

        @Inject
        var oneResultPresenter = OneResultPresenter()

        override fun getPresenter() = oneResultPresenter

        override fun setModel(oneResult: OneResult) = oneResultPresenter.setOneResult(oneResult)

        override fun showPhoto(url: String) = with(itemView) {
            photo_image_view.loadPhoto(url)
        }

        override fun showName(name: String) = with(itemView) {
            name_text_view.text = name
        }

        override fun showPercent(percent: Double) = with(itemView) {
            percent_text_view.text = percent.toString() + "%"
        }

        override fun showQuantity(quantity: Int) = with(itemView) {
            quantity_text_view.text = quantity.toString()
        }

    }

}