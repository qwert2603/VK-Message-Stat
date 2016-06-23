package com.qwert2603.vkmessagestat.base.recyclerview

/**
 * Callback для долгого нажатия на элемент.
 */
interface LongClickCallbacks {
    /**
     * Долгое нажатие на элемент.
     *
     * @param position позиция элемента, на который было долгое нажатие
     */
    fun onItemLongClicked(position: Int)
}
