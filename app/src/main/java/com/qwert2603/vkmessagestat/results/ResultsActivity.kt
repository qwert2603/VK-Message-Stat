package com.qwert2603.vkmessagestat.results

import com.qwert2603.vkmessagestat.base.BaseFragment
import com.qwert2603.vkmessagestat.base.BasePresenter
import com.qwert2603.vkmessagestat.base.SingleFragmentActivity

class ResultsActivity : SingleFragmentActivity() {
    override fun createFragment(): BaseFragment<out BasePresenter<*, *>> = ResultsFragment.newInstance()
}