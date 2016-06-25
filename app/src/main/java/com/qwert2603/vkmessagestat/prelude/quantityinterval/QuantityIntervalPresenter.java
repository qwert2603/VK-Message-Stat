package com.qwert2603.vkmessagestat.prelude.quantityinterval;

import android.support.annotation.NonNull;

import com.qwert2603.vkmessagestat.R;
import com.qwert2603.vkmessagestat.base.BasePresenter;
import com.qwert2603.vkmessagestat.model.QuantityInterval;

public class QuantityIntervalPresenter extends BasePresenter<QuantityInterval, QuantityIntervalView> {

    public void setInterval(QuantityInterval interval) {
        setModel(interval);
    }

    @Override
    protected void onUpdateView(@NonNull QuantityIntervalView view) {
        QuantityInterval interval = getModel();
        if (interval != null) {
            view.showIntervalImage(R.drawable.last);
            view.showQuantityInterval(interval);
        }
    }
}
