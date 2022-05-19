package com.example.customimagelist.demo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class DemoViewModel(
    savedStateHandle: SavedStateHandle,
    dataProvider: DemoDataProvider = DemoDataProvider()
) : ViewModel() {

    val images: MutableLiveData<List<String>> =
        savedStateHandle.getLiveData(STATE_KEY_IMAGES, dataProvider.imageUrlList)

    fun onShuffleClick() {
        images.value?.let { images.value = it.shuffled() }
    }

    companion object {
        private const val STATE_KEY_IMAGES = "DemoViewModel_keyImages"
    }

}
