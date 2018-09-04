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
            if (recyclerView.top < scrollView.getVisibleRect().top) {
                scrollView.smoothScrollTo(0, recyclerView.top)
            } else if (recyclerView.bottom > scrollView.getVisibleRect().bottom) {
                val scrollY = scrollView.scrollY + recyclerView.bottom - scrollView.getVisibleRect().bottom
                scrollView.smoothScrollTo(0, scrollY)
            }
        }
        prevState = newState
    }
}