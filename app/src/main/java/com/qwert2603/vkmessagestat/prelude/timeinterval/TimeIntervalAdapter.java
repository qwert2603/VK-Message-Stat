package com.qwert2603.vkmessagestat.prelude.timeinterval;

import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qwert2603.vkmessagestat.R;
import com.qwert2603.vkmessagestat.VkMessageStatApplication;
import com.qwert2603.vkmessagestat.base.BaseRecyclerViewAdapter;
import com.qwert2603.vkmessagestat.model.TimeInterval;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimeIntervalAdapter
        extends BaseRecyclerViewAdapter<TimeInterval, TimeIntervalAdapter.TimeIntervalViewHolder, TimeIntervalPresenter> {

    public TimeIntervalAdapter() {
    }

    @Override
    public TimeIntervalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_interval, parent, false);
        return new TimeIntervalViewHolder(view);
    }

    public class TimeIntervalViewHolder
            extends BaseRecyclerViewAdapter<TimeInterval, ?, TimeIntervalPresenter>.RecyclerViewHolder
            implements TimeIntervalView {

        @Inject
        TimeIntervalPresenter mTimeIntervalPresenter;

        @BindView(R.id.interval_image_view)
        ImageView mIntervalImageView;

        @BindView(R.id.interval_text_view)
        TextView mIntervalTextView;

        public TimeIntervalViewHolder(View itemView) {
            super(itemView);
            VkMessageStatApplication.getAppComponent().inject(TimeIntervalViewHolder.this);
            ButterKnife.bind(TimeIntervalViewHolder.this, itemView);
        }

        @Override
        protected TimeIntervalPresenter getPresenter() {
            return mTimeIntervalPresenter;
        }

        @Override
        protected void setModel(TimeInterval interval) {
            mTimeIntervalPresenter.setInterval(interval);
        }

        @Override
        public void showIntervalImage(@DrawableRes int drawableRes) {
            mIntervalImageView.setImageResource(drawableRes);
        }

        @Override
        public void showTimeInterval(int hours) {
            String string = itemView.getResources().getQuantityString(R.plurals.hours, hours, hours);
            mIntervalTextView.setText(string);
        }
    }

}
