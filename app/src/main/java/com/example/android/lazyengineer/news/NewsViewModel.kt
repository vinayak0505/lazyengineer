package com.example.android.lazyengineer.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.lazyengineer.LazyEngineerApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository)
    : ViewModel() {

    private var job =  Job()
    private val coroutineScope = CoroutineScope(job + Dispatchers.Main)

    private val _news = MutableLiveData<NewsResponse>()
    val news: LiveData<NewsResponse>
        get() = _news

    fun getNews() {
        coroutineScope.launch {
            val getNewsDeferred  = LazyEngineerApi.retrofitService.getNews()
            try {
                _news.value = getNewsDeferred.body()
            }
            catch (t: Throwable){}
        }


    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}