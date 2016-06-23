package com.qwert2603.vkmessagestat.prelude.quantityinterval;

import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qwert2603.vkmessagestat.R;
import com.qwert2603.vkmessagestat.VkMessageStatApplication;
import com.qwert2603.vkmessagestat.base.BaseRecyclerViewAdapter;
import com.qwert2603.vkmessagestat.model.QuantityInterval;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuantityIntervalAdapter
        extends BaseRecyclerViewAdapter<QuantityInterval, QuantityIntervalAdapter.QuantityIntervalViewHolder, QuantityIntervalPresenter> {

    public QuantityIntervalAdapter() {
    }

    @Override
    public QuantityIntervalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_interval, parent, false);
        return new QuantityIntervalViewHolder(view);
    }

    public class QuantityIntervalViewHolder
            extends BaseRecyclerViewAdapter<QuantityInterval, ?, QuantityIntervalPresenter>.RecyclerViewHolder
            implements QuantityIntervalView {

        @Inject
        QuantityIntervalPresenter mQuantityIntervalPresenter;

        @BindView(R.id.interval_image_view)
        ImageView mIntervalImageView;

        @BindView(R.id.interval_text_view)
        TextView mIntervalTextView;

        public QuantityIntervalViewHolder(View itemView) {
            super(itemView);
            VkMessageStatApplication.getAppComponent().inject(QuantityIntervalViewHolder.this);
            ButterKnife.bind(QuantityIntervalViewHolder.this, itemView);
        }

        @Override
        protected QuantityIntervalPresenter getPresenter() {
            return mQuantityIntervalPresenter;
        }

        @Override
        protected void setModel(QuantityInterval interval) {
            mQuantityIntervalPresenter.setInterval(interval);
        }

        @Override
        public void showIntervalImage(@DrawableRes int drawableRes) {
            mIntervalImageView.setImageResource(drawableRes);
        }

        @Override
        public void showQuantityInterval(int quantity) {
            String string = itemView.getResources().getQuantityString(R.plurals.quantities, quantity, quantity);
            if (quantity <= 0) {
                string = string.replace("-1", itemView.getResources().getString(R.string.all));
            }
            mIntervalTextView.setText(string);
        }
    }

}
