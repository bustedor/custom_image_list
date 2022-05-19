package com.example.customimagelist.views.assignmentview

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

class AssignmentView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {

    init {
        adapter = AssignmentViewListAdapter()
    }

    fun setImageUrlList(imageUrlList: List<String>) {
        (adapter as? AssignmentViewListAdapter)?.submitList(imageUrlList)
    }

}
