package com.qwert2603.vkmessagestat.oneresult

import android.view.View
import com.qwert2603.vkmessagestat.VkMessageStatApplication
import com.qwert2603.vkmessagestat.base.recyclerview.BaseRecyclerViewAdapter
import com.qwert2603.vkmessagestat.base.recyclerview.BaseRecyclerViewHolder
import com.qwert2603.vkmessagestat.loadPhoto
import com.qwert2603.vkmessagestat.mock.MockOneResultPresenter
import com.qwert2603.vkmessagestat.mock.Mocks
import com.qwert2603.vkmessagestat.model.OneResult
import kotlinx.android.synthetic.main.item_one_result.view.*
import javax.inject.Inject

class OneResultViewHolder(itemView: View?, adapter: BaseRecyclerViewAdapter<OneResult, OneResultViewHolder>) :
        BaseRecyclerViewHolder<OneResult, OneResultPresenter>(itemView, adapter), OneResultView {

    @Inject @JvmField
    var oneResultPresenter : OneResultPresenter = MockOneResultPresenter()

    override fun bindPresenter() {
        if (oneResultPresenter == Mocks.MOCK_ONE_RESULT_PRESENTER) {
            VkMessageStatApplication.getAppComponent().inject(this);
        }
        super.bindPresenter()
    }

    override fun getPresenter() = oneResultPresenter

    override fun setModel(oneResult: OneResult) = oneResultPresenter.setOneResult(oneResult)

    override fun showPhoto(url: String) = with(itemView) {
        photo_image_view.loadPhoto(url)
    }

    override fun showName(name: String) = with(itemView) {
        name_text_view.text = name
    }

    override fun showPercent(percent: Double) = with(itemView) {
        percent_text_view.text = String.format("%.2f%%", percent)
    }

    override fun showQuantity(quantity: Int) = with(itemView) {
        quantity_text_view.text = quantity.toString()
    }

}