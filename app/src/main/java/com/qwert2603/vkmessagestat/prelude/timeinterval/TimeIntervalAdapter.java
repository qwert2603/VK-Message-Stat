package com.qwert2603.vkmessagestat.prelude.timeinterval;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qwert2603.vkmessagestat.R;
import com.qwert2603.vkmessagestat.base.recyclerview.BaseRecyclerViewAdapter;
import com.qwert2603.vkmessagestat.model.TimeInterval;

public class TimeIntervalAdapter extends BaseRecyclerViewAdapter<TimeInterval, TimeIntervalViewHolder> {

    public TimeIntervalAdapter() {
    }

    @Override
    public TimeIntervalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_interval, parent, false);
        return new TimeIntervalViewHolder(view, TimeIntervalAdapter.this);
    }

}
