package com.example.customimagelist.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.customimagelist.R
import com.example.customimagelist.views.assignmentview.LoadImageListener

@BindingAdapter("imageUrl", "listener", requireAll = true)
fun bindImageFromUrl(view: ImageView, imageUrl: String?, loadImageListener: LoadImageListener) {
    if (imageUrl.isNullOrBlank()) {
        return
    }
    loadImageListener.setTimeBeforeLoad(System.currentTimeMillis())
    Glide.with(view.context)
        .load(imageUrl)
        .placeholder(R.drawable.ic_placeholder_image)
        .error(R.drawable.ic_broken_image)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .listener(loadImageListener)
        .into(view)
}
