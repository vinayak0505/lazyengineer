package com.example.android.lazyengineer.news

class NewsRepository(
        private val api: NewsApi
):SafeApiRequest() {

    suspend fun getNews() = apiRequest { api.getNews() }
}