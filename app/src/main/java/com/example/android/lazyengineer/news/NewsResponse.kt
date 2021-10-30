package com.example.android.lazyengineer.news

data class NewsResponse(
    val count: Int,
    val next: String,
    val previous: Any?,
    val results: List<News>
)
