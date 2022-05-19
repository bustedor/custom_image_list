package com.example.customimagelist.views.assignmentview

import android.graphics.drawable.Drawable
import android.util.Log
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.customimagelist.network.LoadTimeRepository

class LoadImageListener(
    private val repository: LoadTimeRepository = LoadTimeRepository()
) : RequestListener<Drawable> {

    // binding adapter sets this field just before
    // loading the image to get more accurate load time
    private var timeBeforeLoad = INVALID_TIME

    fun setTimeBeforeLoad(time: Long) {
        timeBeforeLoad = time
    }

    override fun onLoadFailed(
        e: GlideException?,
        model: Any?,
        target: Target<Drawable>?,
        isFirstResource: Boolean
    ): Boolean {
        // NO-OP
        return false
    }

    override fun onResourceReady(
        resource: Drawable?,
        model: Any?,
        target: Target<Drawable>?,
        dataSource: DataSource?,
        isFirstResource: Boolean
    ): Boolean {
        if (timeBeforeLoad != INVALID_TIME) {
            val loadTime = System.currentTimeMillis() - timeBeforeLoad
            Log.d(TAG, "Loading image took $loadTime milliseconds.")
            repository.sendLoadTime(loadTime)
        }
        return false
    }

    companion object {
        private const val INVALID_TIME = -1L
        private const val TAG = "Tag_LoadImageListener"
    }

}
