package com.qwert2603.vkmessagestat.base.recyclerview;

/**
 * Callback для нажатия на элемент.
 */
public interface ClickCallbacks {
    /**
     * Нажатие на элемент.
     *
     * @param position позиция нажатого элемента
     */
    void onItemClicked(int position);
}
