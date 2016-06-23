package com.qwert2603.vkmessagestat.prelude.timeinterval;

import android.support.annotation.DrawableRes;

import com.qwert2603.vkmessagestat.base.BaseView;

public interface TimeIntervalView extends BaseView {
    void showTimeInterval(int hours);
    void showIntervalImage(@DrawableRes int drawableRes);
}
