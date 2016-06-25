package com.qwert2603.vkmessagestat.prelude.timeinterval;

import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qwert2603.vkmessagestat.R;
import com.qwert2603.vkmessagestat.VkMessageStatApplication;
import com.qwert2603.vkmessagestat.base.recyclerview.BaseRecyclerViewAdapter;
import com.qwert2603.vkmessagestat.base.recyclerview.BaseRecyclerViewHolder;
import com.qwert2603.vkmessagestat.model.TimeInterval;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimeIntervalViewHolder
        extends BaseRecyclerViewHolder<TimeInterval, TimeIntervalPresenter>
        implements TimeIntervalView {

    @Inject
    TimeIntervalPresenter mTimeIntervalPresenter;

    @BindView(R.id.interval_image_view)
    ImageView mIntervalImageView;

    @BindView(R.id.interval_text_view)
    TextView mIntervalTextView;

    public TimeIntervalViewHolder(View itemView, BaseRecyclerViewAdapter adapter) {
        super(itemView,adapter);
        VkMessageStatApplication.getAppComponent().inject(TimeIntervalViewHolder.this);
        ButterKnife.bind(TimeIntervalViewHolder.this, itemView);
    }

    @Override
    protected TimeIntervalPresenter getPresenter() {
        return mTimeIntervalPresenter;
    }

    @Override
    public void setModel(TimeInterval interval) {
        mTimeIntervalPresenter.setInterval(interval);
    }

    @Override
    public void showIntervalImage(@DrawableRes int drawableRes) {
        mIntervalImageView.setImageResource(drawableRes);
    }

    @Override
    public void showTimeInterval(@NotNull TimeInterval interval) {
        mIntervalTextView.setText(interval.toString(itemView.getContext()));
    }
}