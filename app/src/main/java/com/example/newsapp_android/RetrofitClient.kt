package com.example.newsapp_android

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object  RetrofitClient {
    val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .readTimeout(90, TimeUnit.SECONDS)
        .connectTimeout(90, TimeUnit.SECONDS)
        .build()

    fun getInstance(): Retrofit {
        var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://superficial-vegetable-production.up.railway.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit
    }

}