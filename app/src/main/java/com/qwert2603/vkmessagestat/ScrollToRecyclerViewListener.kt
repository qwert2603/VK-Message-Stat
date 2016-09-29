package com.qwert2603.vkmessagestat

import android.support.v7.widget.RecyclerView
import android.widget.ScrollView

/**
 * Слушатель, который прокручивает вертикальный ScrollView,
 * чтобы горизонтальный RecyclerView был полностью виде после прокрутки.
 */
class ScrollToRecyclerViewListener(val scrollView: ScrollView) : RecyclerView.OnScrollListener() {
    var prevState: Int = RecyclerView.SCROLL_STATE_IDLE

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        if (prevState == RecyclerView.SCROLL_STATE_DRAGGING && newState != prevState) {
            if (recyclerView.y < scrollView.getVisibleRect().top) {
                scrollView.scrollY = recyclerView.y.toInt()
            } else if (recyclerView.y + recyclerView.height > scrollView.getVisibleRect().bottom) {
                scrollView.scrollY = scrollView.scrollY +
                        (recyclerView.y.toInt() + recyclerView.height - scrollView.getVisibleRect().bottom)
            }
        }
        prevState = newState
    }
}