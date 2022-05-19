package com.example.customimagelist.util

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ScrollToFistLinearLayoutManager(
    context: Context,
    @RecyclerView.Orientation orientation: Int = RecyclerView.HORIZONTAL,
    reverseLayout: Boolean = false,
) : LinearLayoutManager(context, orientation, reverseLayout) {

    override fun onItemsMoved(recyclerView: RecyclerView, from: Int, to: Int, itemCount: Int) {
        super.onItemsMoved(recyclerView, from, to, itemCount)
        recyclerView.smoothScrollToPosition(POSITION_FIRST)
    }

    companion object {
        private const val POSITION_FIRST = 0
    }

}
