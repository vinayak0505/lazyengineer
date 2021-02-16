package com.example.android.lazyengineer.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class NewsViewModelFactory(
        private val repository: NewsRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T:ViewModel?> create(modelClass: Class<T>):T{
        return NewsViewModel(repository) as T
    }
}