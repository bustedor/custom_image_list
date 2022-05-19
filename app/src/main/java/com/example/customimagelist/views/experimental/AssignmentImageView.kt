package com.example.customimagelist.views.experimental

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import androidx.savedstate.findViewTreeSavedStateRegistryOwner
import com.example.customimagelist.databinding.LayoutAssignmentImageViewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

/**
 * Custom image view that has its own viewModel and repository.
 * When initializing, loads image as bitmap from the given url,
 * logs the load time and displays the image.
 */
class AssignmentImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    // TODO provide public setter
    private val imageUrl: String = "https://db62cod6cnasq.cloudfront.net/user-media/0/image1-500kb.png"

    private val binding = LayoutAssignmentImageViewBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    private val viewModel by lazy {
        val factory = AssignmentImageViewModel.Factory(findViewTreeSavedStateRegistryOwner()!!, imageUrl)
        ViewModelProvider(findViewTreeViewModelStoreOwner()!!, factory)
            .get(AssignmentImageViewModel::class.java)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        // TODO find a better way to instantiate viewModel since onAttachedToWindow is too late
        findViewTreeLifecycleOwner()?.let { lifecycleOwner ->
            binding.lifecycleOwner = lifecycleOwner
            binding.viewModel = viewModel
        }
    }

}

class AssignmentImageViewModel(
    imageUrl: String,
    private val repository: AssignmentImageViewRepository = AssignmentImageViewRepository()
) : ViewModel() {

    val imageBitmap: LiveData<Bitmap?> = repository.imageWithLoadTime.map { imageWithLoadTime ->
        imageWithLoadTime.imageBitmap?.let {
            Log.d(TAG, "Loading image took ${imageWithLoadTime.loadTime} millis.")
            // TODO repository.sendImageLoadTime(imageWithLoadTime.loadTime)

        }
        imageWithLoadTime.imageBitmap
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchImageFromUrl(imageUrl)
        }
    }

    companion object {
        private const val TAG = "Tag_AssignmentImageVM"
    }

    class Factory(
        owner: SavedStateRegistryOwner,
        private val imageUrl: String
    ) : AbstractSavedStateViewModelFactory(owner, null) {
        override fun <T : ViewModel> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
        ): T {
            @Suppress("UNCHECKED_CAST")
            return AssignmentImageViewModel(imageUrl) as T
        }
    }

}

class AssignmentImageViewRepository {
    val imageWithLoadTime = MutableLiveData<ImageWithLoadTime>()

    fun fetchImageFromUrl(imageUrl: String) {
        val timeBeforeLoad = System.currentTimeMillis()
        val loadedBitmap = runCatching {
            BitmapFactory.decodeStream(URL(imageUrl).openConnection().getInputStream())
        }.getOrNull()
        val loadTime = System.currentTimeMillis() - timeBeforeLoad
        imageWithLoadTime.postValue(ImageWithLoadTime(loadedBitmap, loadTime))
    }
}

data class ImageWithLoadTime(
    val imageBitmap: Bitmap?,
    val loadTime: Long
)
