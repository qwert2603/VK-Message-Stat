package com.qwert2603.vkmessagestat.results

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qwert2603.vkmessagestat.R
import com.qwert2603.vkmessagestat.VkMessageStatApplication
import com.qwert2603.vkmessagestat.base.BaseFragment
import com.qwert2603.vkmessagestat.inflate
import com.qwert2603.vkmessagestat.model.OneResult
import com.qwert2603.vkmessagestat.showIfNotYet
import com.qwert2603.vkmessagestat.util.LogUtils
import kotlinx.android.synthetic.main.fragment_results.*
import javax.inject.Inject

open class ResultsFragment : BaseFragment<ResultsPresenter>(), ResultsView {

    companion object {
        fun newInstance() = ResultsFragment()
    }

    @Inject @JvmField
    var resultsPresenter = ResultsPresenter()

    @Inject @JvmField
    var resultsAdapter = ResultsAdapter()

    override fun getPresenter() = resultsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        VkMessageStatApplication.getAppComponent().inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_results)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        results_recycler_view.layoutManager = LinearLayoutManager(activity)
        results_recycler_view.adapter = resultsAdapter
        LogUtils.d(resultsAdapter.toString())
    }

    override fun showLayer(layer: Layer) {
        when (layer) {
            Layer.CALCULATING -> view_animator.showIfNotYet(0)
            Layer.RESULTS -> view_animator.showIfNotYet(1)
        }
    }

    override fun showProgress(process: Int) {
        progress_bar.progress = process
    }

    override fun showTotal(total: Int) {
        total_text_view.text = total.toString()
    }

    override fun showDone(done: Int) {
        done_text_view.text = done.toString()
    }

    override fun showResultList(oneResults: List<OneResult>) {
        throw UnsupportedOperationException()
    }
}
