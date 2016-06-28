package com.qwert2603.vkmessagestat.results

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qwert2603.vkmessagestat.R
import com.qwert2603.vkmessagestat.VkMessageStatApplication
import com.qwert2603.vkmessagestat.base.BaseFragment
import com.qwert2603.vkmessagestat.base.SingleFragmentActivity
import com.qwert2603.vkmessagestat.inflate
import com.qwert2603.vkmessagestat.model.OneResult
import com.qwert2603.vkmessagestat.showIfNotYet
import kotlinx.android.synthetic.main.fragment_results.*
import javax.inject.Inject

open class ResultsFragment : BaseFragment<ResultsPresenter>(), ResultsView {

    companion object {
        @JvmField val INTERVAL_TYPE_ORDINAL_KEY = "interval_type_ordinal"
        @JvmField val INTERVAL_VALUE_KEY = "interval_value"

        fun newInstance(args: Bundle): ResultsFragment {
            val fragment = ResultsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject @JvmField
    var resultsPresenter = ResultsPresenter()

    @Inject @JvmField
    var resultsAdapter = ResultsAdapter()

    override fun getPresenter() = resultsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        VkMessageStatApplication.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
        val intervalType = IntervalType.values()[arguments.getInt(INTERVAL_TYPE_ORDINAL_KEY)]
        val value = arguments.getInt(INTERVAL_VALUE_KEY)
        resultsPresenter.setInterval(intervalType, value)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_results)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        results_recycler_view.layoutManager = LinearLayoutManager(activity)
        results_recycler_view.adapter = resultsAdapter

        view_animator.getChildAt(Layer.LOADING_ERROR.ordinal).setOnClickListener { resultsPresenter.onReloadClicked() }
    }

    override fun showTitle(title: String) {
        (activity as SingleFragmentActivity).setTitle(title)
    }

    override fun showLayer(layer: Layer) {
        view_animator.showIfNotYet(layer.ordinal)
    }

    override fun showProgress(process: Int) {
        progress_bar.progress = process
    }

    override fun showTotal(total: String) {
        total_text_view.text = total
    }

    override fun showDone(done: Int) {
        done_text_view.text = done.toString()
    }

    override fun showResultList(oneResults: List<OneResult>) {
        resultsAdapter.setModelList(oneResults)
    }

    override fun showNoInternet() {
        Snackbar.make(view_animator, R.string.no_internet, Snackbar.LENGTH_SHORT).show()
    }
}
