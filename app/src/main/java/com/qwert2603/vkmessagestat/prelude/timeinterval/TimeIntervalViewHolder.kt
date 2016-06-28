package com.qwert2603.vkmessagestat.prelude.timeinterval;

import android.support.annotation.DrawableRes
import android.view.View
import com.qwert2603.vkmessagestat.VkMessageStatApplication
import com.qwert2603.vkmessagestat.base.recyclerview.BaseRecyclerViewAdapter
import com.qwert2603.vkmessagestat.base.recyclerview.BaseRecyclerViewHolder
import com.qwert2603.vkmessagestat.mock.MockTimeIntervalPresenter
import com.qwert2603.vkmessagestat.mock.Mocks
import com.qwert2603.vkmessagestat.model.TimeInterval
import kotlinx.android.synthetic.main.item_interval.view.*
import javax.inject.Inject

class TimeIntervalViewHolder(itemView: View, adapter: BaseRecyclerViewAdapter<TimeInterval, TimeIntervalViewHolder>)
: BaseRecyclerViewHolder<TimeInterval, TimeIntervalPresenter>(itemView, adapter), TimeIntervalView {

    @Inject @JvmField
    var mTimeIntervalPresenter : TimeIntervalPresenter = MockTimeIntervalPresenter()

    override fun bindPresenter() {
        if (mTimeIntervalPresenter == Mocks.MOCK_TIME_INTERVAL_PRESENTER) {
            VkMessageStatApplication.getAppComponent().inject(this);
        }
        super.bindPresenter()
    }

    override fun getPresenter() = mTimeIntervalPresenter

    override fun setModel(model: TimeInterval?) {
        mTimeIntervalPresenter.setInterval(model)
    }

    override fun showIntervalImage(@DrawableRes drawableRes: Int) = with(itemView) {
        interval_image_view.setImageResource(drawableRes)
    }

    override fun showTimeInterval(interval: TimeInterval) = with(itemView) {
        interval_text_view.text = interval.toString(itemView.context)
    }

}