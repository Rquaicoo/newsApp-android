package com.example.newsapp_android

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    fun getInstance(): Retrofit {
        var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://superficial-vegetable-production.up.railway.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

}