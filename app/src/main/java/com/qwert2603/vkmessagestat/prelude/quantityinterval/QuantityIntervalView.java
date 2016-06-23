package com.qwert2603.vkmessagestat.prelude.quantityinterval;

import android.support.annotation.DrawableRes;

import com.qwert2603.vkmessagestat.base.BaseView;

public interface QuantityIntervalView extends BaseView {
    void showQuantityInterval(int quantity);
    void showIntervalImage(@DrawableRes int drawableRes);
}
