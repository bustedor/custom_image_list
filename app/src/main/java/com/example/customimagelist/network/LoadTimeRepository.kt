package com.example.customimagelist.network

import android.util.Log
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoadTimeRepository(private val loadTimeService: LoadTimeService = LoadTimeService.create()) {

    fun sendLoadTime(loadTime: Long) {
        loadTimeService.sendLoadTime(loadTime).enqueue(
            object : Callback<ResponseBody> {

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        Log.d(TAG, "Load time sent successfully.")
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    // NO-OP
                }

            }
        )
    }

    companion object {
        private const val TAG = "Tag_LoadTimeRepository"
    }

}
