package com.example.android.lazyengineer.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.lazyengineer.util.Coroutines
import kotlinx.coroutines.Job

class NewsViewModel(
        private val repository: NewsRepository
) : ViewModel() {
    private lateinit var job: Job
    private val _news = MutableLiveData<NewsResponse>()
    val news: LiveData<NewsResponse>
        get() = _news

    fun getNews() {
        job = Coroutines.ioThenMain(
                { repository.getNews() }, { _news.value = it }
        )

    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }
}