package com.qwert2603.vkmessagestat.base

import android.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.qwert2603.vkmessagestat.R
import kotlinx.android.synthetic.main.activity_fragment.*

abstract class SingleFragmentActivity : AppCompatActivity() {

    protected abstract fun createFragment(): BaseFragment<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fragment)
        setSupportActionBar(toolbar)

        var fragment: Fragment? = fragmentManager.findFragmentById(R.id.fragment_container)
        if (fragment == null) {
            fragment = createFragment()
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commitAllowingStateLoss()
        }
    }

    fun setTitle(title: String) {
        toolbar_title_text_view.text=title
    }
}
