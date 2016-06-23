package com.qwert2603.vkmessagestat.base.recyclerview;

/**
 * Callback для долгого нажатия на элемент.
 */
public interface LongClickCallbacks {
    /**
     * Долгое нажатие на элемент.
     *
     * @param position позиция элемента, на который было долгое нажатие
     */
    void onItemLongClicked(int position);
}
