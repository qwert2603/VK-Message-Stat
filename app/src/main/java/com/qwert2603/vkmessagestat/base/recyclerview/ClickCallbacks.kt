package com.qwert2603.vkmessagestat.base.recyclerview

/**
 * Callback для нажатия на элемент.
 */
interface ClickCallbacks {
    /**
     * Нажатие на элемент.
     *
     * @param position позиция нажатого элемента
     */
    fun onItemClicked(position: Int)
}
