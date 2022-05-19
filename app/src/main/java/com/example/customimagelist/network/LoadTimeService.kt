package com.example.customimagelist.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface LoadTimeService {

    @POST("post")
    fun sendLoadTime(
        @Body
        loadTimeAsMillis: Long
    ): Call<ResponseBody>

    companion object {
        private const val BASE_URL = "https://httpbin.org/"

        fun create() : LoadTimeService {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(LoadTimeService::class.java)
        }
    }

}
