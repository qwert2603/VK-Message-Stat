package com.qwert2603.vkmessagestat.prelude.quantityinterval;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qwert2603.vkmessagestat.R;
import com.qwert2603.vkmessagestat.base.recyclerview.BaseRecyclerViewAdapter;
import com.qwert2603.vkmessagestat.model.QuantityInterval;

public class QuantityIntervalAdapter extends BaseRecyclerViewAdapter<QuantityInterval, QuantityIntervalViewHolder> {

    public QuantityIntervalAdapter() {
    }

    @Override
    public QuantityIntervalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_interval, parent, false);
        return new QuantityIntervalViewHolder(view, QuantityIntervalAdapter.this);
    }


}
