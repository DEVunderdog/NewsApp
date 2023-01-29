package com.somename.newsapp.network

import com.squareup.moshi.Json

data class NewsData(

    val newsPublication: String,

    val articleAuthor:String,

    val newsHeadline: String,

    val newsUrl: String,

    val newsImage:String,

    val publishedAt: String,

    val newsContent:String
)

data class NewsResponse(
    val result : List<NewsData>
)
