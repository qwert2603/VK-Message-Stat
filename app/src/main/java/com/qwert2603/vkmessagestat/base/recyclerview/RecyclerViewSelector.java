package com.qwert2603.vkmessagestat.base.recyclerview;

import android.view.View;

/**
 * Класс для выделения отдельного элемента RecyclerView.
 */
public class RecyclerViewSelector {

    private BaseRecyclerViewAdapter mAdapter;

    private int mSelectedPosition = -1;

    public RecyclerViewSelector(BaseRecyclerViewAdapter adapter) {
        mAdapter = adapter;
    }

    /**
     * Установить позицию выделенного элемент.
     *
     * @param selectedPosition позиция выделенного элемента.
     */
    public void setSelectedPosition(int selectedPosition) {
        int oldSelectedPosition = mSelectedPosition;
        mSelectedPosition = selectedPosition;
        mAdapter.notifyItemChanged(oldSelectedPosition);
        mAdapter.notifyItemChanged(mSelectedPosition);
    }

    /**
     * Отобразить выделен элемент или нет.
     *
     * @param itemView view элемента.
     * @param position позиция элемента.
     */
    public void showWhetherItemSelected(View itemView, int position) {
        itemView.setSelected(position == mSelectedPosition);
    }
}
