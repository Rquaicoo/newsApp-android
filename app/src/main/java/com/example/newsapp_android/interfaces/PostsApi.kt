package com.example.newsapp_android.interfaces

import com.example.newsapp_android.dataclasses.PostsModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface PostsApi {
    @Headers(
        "Accept: application/json"
    )
    @GET("v1/news/news")
    suspend fun getUserFeed(): Response<List<PostsModel>>
}