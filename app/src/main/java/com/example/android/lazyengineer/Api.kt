package com.example.android.lazyengineer

import com.example.android.lazyengineer.news.NewsResponse
import com.example.android.lazyengineer.syllabus.SyllabusObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("btechsyll")
    suspend fun getBTechSyllabusAsync() : Response<SyllabusObject>

    @GET("whatsnews")
    suspend fun getNews() : Response<NewsResponse>

    @GET("https://lazyengineer.tech/leapi/login/?username={username}&password={password}")
    suspend fun putLogin(
        @Path("username") username:String,
        @Path("password") password:String
    ) : Response<NewsResponse>
}