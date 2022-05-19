package com.example.customimagelist.demo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class DemoViewModel(
    savedStateHandle: SavedStateHandle,
    dataProvider: DemoDataProvider = DemoDataProvider()
) : ViewModel() {

    val images: MutableLiveData<List<String>> = MutableLiveData(dataProvider.imageUrlList)

}
