package com.qwert2603.vkmessagestat.results

import android.os.Bundle
import com.qwert2603.vkmessagestat.base.SingleFragmentActivity

class ResultsActivity : SingleFragmentActivity() {
    override fun createFragment() = ResultsFragment.newInstance(intent.extras!!)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}