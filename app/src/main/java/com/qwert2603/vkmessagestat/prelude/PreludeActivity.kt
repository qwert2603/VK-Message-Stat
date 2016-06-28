package com.qwert2603.vkmessagestat.prelude;

import com.qwert2603.vkmessagestat.base.SingleFragmentActivity

class PreludeActivity : SingleFragmentActivity() {
    override fun createFragment() = PreludeFragment.newInstance()
}
