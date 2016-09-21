package com.qwert2603.vkmessagestat.prelude

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.qwert2603.vkmessagestat.R
import com.qwert2603.vkmessagestat.VkMessageStatApplication
import com.qwert2603.vkmessagestat.base.BaseFragment
import com.qwert2603.vkmessagestat.base.recyclerview.ClickCallbacks
import com.qwert2603.vkmessagestat.inflate
import com.qwert2603.vkmessagestat.login.MainActivity
import com.qwert2603.vkmessagestat.model.QuantityInterval
import com.qwert2603.vkmessagestat.model.TimeInterval
import com.qwert2603.vkmessagestat.prelude.quantityinterval.QuantityIntervalAdapter
import com.qwert2603.vkmessagestat.prelude.timeinterval.TimeIntervalAdapter
import com.qwert2603.vkmessagestat.results.IntervalType
import com.qwert2603.vkmessagestat.results.ResultsActivity
import com.qwert2603.vkmessagestat.results.ResultsFragment
import kotlinx.android.synthetic.main.fragment_prelude.*
import javax.inject.Inject

class PreludeFragment :  BaseFragment<PreludePresenter>(), PreludeView {

    companion object {
        fun newInstance() : PreludeFragment = PreludeFragment()
    }

    @Inject @JvmField
    var mPreludePresenter = PreludePresenter()

    @Inject @JvmField
    var mTimeIntervalAdapter = TimeIntervalAdapter()

    @Inject @JvmField
    var mQuantityIntervalAdapter = QuantityIntervalAdapter()

    override fun getPresenter() = mPreludePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        VkMessageStatApplication.getAppComponent().inject(this@PreludeFragment)
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_prelude)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        time_interval_recycler_view.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        time_interval_recycler_view.adapter = mTimeIntervalAdapter
        mTimeIntervalAdapter.clickCallbacks = object : ClickCallbacks {
            override fun onItemClicked(position: Int) {
                mPreludePresenter.onTimeSelected(position)
            }
        }

        quantity_interval_recycler_view.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        quantity_interval_recycler_view.adapter = mQuantityIntervalAdapter
        mQuantityIntervalAdapter.clickCallbacks = object : ClickCallbacks {
            override fun onItemClicked(position: Int) {
                mPreludePresenter.onQuantitySelected(position)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.prelude, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.log_out) {
            mPreludePresenter.onLogOutClicked()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showTimes(times: List<TimeInterval>) {
        mTimeIntervalAdapter.setModelList(times)
    }

    override fun showQuantities(quantities: List<QuantityInterval>) {
        mQuantityIntervalAdapter.setModelList(quantities)
    }

    override fun moveToResults(intervalType: IntervalType, value: Int) {
        val intent = Intent(activity, ResultsActivity::class.java)
        intent.putExtra(ResultsFragment.INTERVAL_TYPE_ORDINAL_KEY, intervalType.ordinal)
        intent.putExtra(ResultsFragment.INTERVAL_VALUE_KEY, value)
        startActivity(intent)
    }

    override fun showLogOut() {
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
        activity.finish()
    }

}
