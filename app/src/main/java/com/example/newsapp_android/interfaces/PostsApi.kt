package com.example.newsapp_android.interfaces

import com.example.newsapp_android.dataclasses.PostsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Url

interface PostsApi {
    @Headers(
        "Accept: application/json"
    )
    @GET("v1/news/news")
    suspend fun getUserFeed(): Response<List<PostsModel>>
}

interface PostByCategoryAPI {
    @Headers(
        "Accept: application/json"
    )
    @GET
    suspend fun getFeedByCategory(@Url url: String) : Response<List<PostsModel>>
}

