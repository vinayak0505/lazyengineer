package com.example.android.lazyengineer.syllabus

data class SyllabusObject(
    val count: Int,
    val next: String,
    val previous: Any?,
    val results: List<Result>
)