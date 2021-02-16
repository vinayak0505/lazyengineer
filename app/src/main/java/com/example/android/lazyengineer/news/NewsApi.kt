package com.example.android.lazyengineer.news

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface NewsApi {

    @GET("whatsnews?page=2")
    suspend fun getNews() : Response<NewsResponse>

    companion object{
        operator fun invoke() : NewsApi {
            return Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://lazyengineer.tech/leapi/")
                    .build()
                    .create(NewsApi::class.java)
        }
    }
}