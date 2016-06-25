package com.qwert2603.vkmessagestat.prelude.timeinterval;

import android.support.annotation.NonNull;

import com.qwert2603.vkmessagestat.R;
import com.qwert2603.vkmessagestat.base.BasePresenter;
import com.qwert2603.vkmessagestat.model.TimeInterval;

public class TimeIntervalPresenter extends BasePresenter<TimeInterval, TimeIntervalView> {

    public void setInterval(TimeInterval interval) {
        setModel(interval);
    }

    @Override
    protected void onUpdateView(@NonNull TimeIntervalView view) {
        TimeInterval interval = getModel();
        if (interval != null) {
            view.showIntervalImage(R.drawable.hours);
            view.showTimeInterval(interval);
        }
    }
}
