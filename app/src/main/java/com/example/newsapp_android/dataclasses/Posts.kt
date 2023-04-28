package com.example.newsapp_android.dataclasses

data class PostsModel(
    var id: Int,
    var title: String,
    var description: String,
    var image: String,
    var url: String,
    var content: String,
    var category: String,
    var date_scraped: String,
    var view_count: Int,
    var author: Author
)
