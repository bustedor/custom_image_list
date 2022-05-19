package com.example.customimagelist.views.assignmentview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.customimagelist.R
import com.example.customimagelist.databinding.LayoutAssignmentViewListItemBinding

class AssignmentViewListAdapter: ListAdapter<String, AssignmentImageViewHolder>(ITEM_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssignmentImageViewHolder {
        return AssignmentImageViewHolder(
            binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.layout_assignment_view_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AssignmentImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {

        val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<String>() {

            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

        }

    }
}

class AssignmentImageViewHolder(
    private val binding: LayoutAssignmentViewListItemBinding,
    private val listener: LoadImageListener = LoadImageListener()
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(imageUrl: String) {
        binding.imageUrl = imageUrl
        binding.listener = listener
    }

}
