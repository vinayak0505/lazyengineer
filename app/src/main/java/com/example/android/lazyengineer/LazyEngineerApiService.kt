package com.example.android.lazyengineer

import android.content.ContentValues.TAG
import android.util.Log
import com.example.android.lazyengineer.news.NewsResponse
import com.example.android.lazyengineer.ui.syllabus.SyllabusObject
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET


private const val BASE_URL = "https://lazyengineer.tech/leapi/"

interface LazyEngineerApiService {
    @GET("btechsyll")
    fun getBTechSyllabusAsync() : Deferred<SyllabusObject>

    @GET("whatsnews?page=2")
    suspend fun getNews() : Deferred<NewsResponse>
}

private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

object LazyEngineerApi {
    val retrofitService: LazyEngineerApiService by lazy {
        retrofit.create(LazyEngineerApiService::class.java)
    }

}