package com.example.customimagelist.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.customimagelist.R

@BindingAdapter("imageUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (imageUrl.isNullOrBlank()) {
        return
    }
    Glide.with(view.context)
        .load(imageUrl)
        .placeholder(R.drawable.ic_placeholder_image)
        .error(R.drawable.ic_broken_image)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(view)
}
