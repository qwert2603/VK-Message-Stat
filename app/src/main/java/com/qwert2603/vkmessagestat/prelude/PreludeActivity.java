package com.qwert2603.vkmessagestat.prelude;

import android.support.annotation.NonNull;

import com.qwert2603.vkmessagestat.base.BaseFragment;
import com.qwert2603.vkmessagestat.base.SingleFragmentActivity;


public class PreludeActivity extends SingleFragmentActivity {
    @NonNull
    @Override
    protected BaseFragment createFragment() {
        return PreludeFragment.newInstance();
    }
}
