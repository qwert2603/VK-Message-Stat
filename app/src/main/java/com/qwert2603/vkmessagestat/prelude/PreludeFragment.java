package com.qwert2603.vkmessagestat.prelude;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qwert2603.vkmessagestat.R;
import com.qwert2603.vkmessagestat.VkMessageStatApplication;
import com.qwert2603.vkmessagestat.base.BaseFragment;
import com.qwert2603.vkmessagestat.model.QuantityInterval;
import com.qwert2603.vkmessagestat.model.TimeInterval;
import com.qwert2603.vkmessagestat.prelude.quantityinterval.QuantityIntervalAdapter;
import com.qwert2603.vkmessagestat.prelude.timeinterval.TimeIntervalAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PreludeFragment extends BaseFragment<PreludePresenter> implements PreludeView {

    public static PreludeFragment newInstance() {
        return new PreludeFragment();
    }

    @BindView(R.id.time_interval_recycler_view)
    RecyclerView mTimeRecyclerView;

    @BindView(R.id.quantity_interval_recycler_view)
    RecyclerView mQuantityRecyclerView;

    @Inject
    PreludePresenter mPreludePresenter;

    @Inject
    TimeIntervalAdapter mTimeIntervalAdapter;

    @Inject
    QuantityIntervalAdapter mQuantityIntervalAdapter;

    @NonNull
    @Override
    protected PreludePresenter getPresenter() {
        return mPreludePresenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        VkMessageStatApplication.getAppComponent().inject(PreludeFragment.this);

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prelude, container, false);
        ButterKnife.bind(PreludeFragment.this, view);

        mTimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mTimeRecyclerView.setAdapter(mTimeIntervalAdapter);
        mTimeIntervalAdapter.setClickCallbacks(position -> mPreludePresenter.onTimeSelected(position));

        mQuantityRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mQuantityRecyclerView.setAdapter(mQuantityIntervalAdapter);
        mQuantityIntervalAdapter.setClickCallbacks(position -> mPreludePresenter.onQuantitySelected(position));

        return view;
    }

    @Override
    public void showTimes(List<TimeInterval> times) {
        mTimeIntervalAdapter.setModelList(times);
    }

    @Override
    public void showQuantities(List<QuantityInterval> quantities) {
        mQuantityIntervalAdapter.setModelList(quantities);
    }
}
