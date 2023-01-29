package com.somename.newsapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.nio.channels.spi.AbstractSelectionKey

object ApiClient {

    private const val BASE_URL = "https://newsapi.org/"

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val retrofit: Retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    val apiService: ApiService by lazy{
        retrofit.create(ApiService::class.java)
    }
}

interface ApiService{

    @GET("v2/top-headlines")
    fun fetchNews(
        @Query("country")
        countryCode:String = "in",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = "my_api_key"
        // I have not written api key but during testing I did and still I couldn't got response.
        ): Call<NewsResponse>
}