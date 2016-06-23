package com.qwert2603.vkmessagestat.prelude.quantityinterval;

import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qwert2603.vkmessagestat.R;
import com.qwert2603.vkmessagestat.VkMessageStatApplication;
import com.qwert2603.vkmessagestat.base.recyclerview.BaseRecyclerViewAdapter;
import com.qwert2603.vkmessagestat.base.recyclerview.BaseRecyclerViewHolder;
import com.qwert2603.vkmessagestat.model.QuantityInterval;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuantityIntervalViewHolder
        extends BaseRecyclerViewHolder<QuantityInterval, QuantityIntervalPresenter>
        implements QuantityIntervalView {

    @Inject
    QuantityIntervalPresenter mQuantityIntervalPresenter;

    @BindView(R.id.interval_image_view)
    ImageView mIntervalImageView;

    @BindView(R.id.interval_text_view)
    TextView mIntervalTextView;

    public QuantityIntervalViewHolder(View itemView, BaseRecyclerViewAdapter adapter) {
        super(itemView, adapter);
        VkMessageStatApplication.getAppComponent().inject(QuantityIntervalViewHolder.this);
        ButterKnife.bind(QuantityIntervalViewHolder.this, itemView);
    }

    @Override
    protected QuantityIntervalPresenter getPresenter() {
        return mQuantityIntervalPresenter;
    }

    @Override
    public void setModel(QuantityInterval interval) {
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
