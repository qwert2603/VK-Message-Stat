package com.qwert2603.vkmessagestat.prelude.quantityinterval

import android.support.annotation.DrawableRes
import android.view.View
import com.qwert2603.vkmessagestat.VkMessageStatApplication
import com.qwert2603.vkmessagestat.base.recyclerview.BaseRecyclerViewAdapter
import com.qwert2603.vkmessagestat.base.recyclerview.BaseRecyclerViewHolder
import com.qwert2603.vkmessagestat.mock.Mocks
import com.qwert2603.vkmessagestat.model.QuantityInterval
import kotlinx.android.synthetic.main.item_interval.view.*
import javax.inject.Inject

class QuantityIntervalViewHolder(itemView: View, adapter: BaseRecyclerViewAdapter<QuantityInterval, QuantityIntervalViewHolder>)
: BaseRecyclerViewHolder<QuantityInterval, QuantityIntervalPresenter>(itemView, adapter), QuantityIntervalView {

    @Inject @JvmField
    var mQuantityIntervalPresenter: QuantityIntervalPresenter = Mocks.MOCK_QUANTITY_INTERVAL_PRESENTER

    override fun bindPresenter() {
        if (mQuantityIntervalPresenter == Mocks.MOCK_QUANTITY_INTERVAL_PRESENTER) {
            VkMessageStatApplication.getAppComponent().inject(this)
        }
        super.bindPresenter()
    }

    override fun getPresenter() = mQuantityIntervalPresenter

    override fun setModel(model: QuantityInterval?) {
        mQuantityIntervalPresenter.setInterval(model)
    }

    override fun showIntervalImage(@DrawableRes drawableRes: Int) = with(itemView) {
        interval_image_view.setImageResource(drawableRes)
    }

    override fun showQuantityInterval(interval: QuantityInterval) = with(itemView) {
        interval_text_view.text = interval.toString(itemView.context)
    }
}
