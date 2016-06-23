package com.qwert2603.vkmessagestat.prelude;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.qwert2603.vkmessagestat.R;
import com.qwert2603.vkmessagestat.VkMessageStatApplication;
import com.qwert2603.vkmessagestat.base.BasePresenter;
import com.qwert2603.vkmessagestat.model.QuantityInterval;
import com.qwert2603.vkmessagestat.model.TimeInterval;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PreludePresenter extends BasePresenter<Object, PreludeView> {

    @Inject
    Context mAppContext;

    private List<TimeInterval> mTimeIntervals;
    private List<QuantityInterval> mQuantityIntervals;

    public PreludePresenter() {
        VkMessageStatApplication.getAppComponent().inject(PreludePresenter.this);
        int[] time_intervals = mAppContext.getResources().getIntArray(R.array.time_intervals);
        mTimeIntervals = new ArrayList<>(time_intervals.length);
        for (int time_interval : time_intervals) {
            mTimeIntervals.add(new TimeInterval(time_interval));
        }

        int[] quantity_intervals = mAppContext.getResources().getIntArray(R.array.quantity_intervals);
        mQuantityIntervals = new ArrayList<>();
        for (int quantity_interval : quantity_intervals) {
            mQuantityIntervals.add(new QuantityInterval(quantity_interval));
        }
    }

    @Override
    protected void onUpdateView(@NonNull PreludeView view) {
        view.showTimes(mTimeIntervals);
        view.showQuantities(mQuantityIntervals);
    }

    public void onTimeSelected(int position) {
        Toast.makeText(mAppContext, String.valueOf(mTimeIntervals.get(position)), Toast.LENGTH_SHORT).show();
    }

    public void onQuantitySelected(int position) {
        Toast.makeText(mAppContext, String.valueOf(mQuantityIntervals.get(position)), Toast.LENGTH_SHORT).show();
    }
}
