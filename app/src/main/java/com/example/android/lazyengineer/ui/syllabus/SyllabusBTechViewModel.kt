package com.example.android.lazyengineer.ui.syllabus

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.lazyengineer.LazyEngineerApi
import com.example.android.lazyengineer.LazyEngineerApiService
import com.example.android.lazyengineer.news.NewsApi
import com.example.android.lazyengineer.news.NewsRepository
import com.example.android.lazyengineer.news.NewsResponse
import com.example.android.lazyengineer.util.Coroutines
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SyllabusBTechViewModel : ViewModel() {
    // TODO: Implement the ViewModel


    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _syllabus = MutableLiveData<List<Result>>()
    val syllabus: LiveData<List<Result>>
        get() = _syllabus


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    init {
        getSyllabus()
    }

    fun getSyllabus(){
        coroutineScope.launch {
            val getSyllabusDeferred = LazyEngineerApi.retrofitService.getBTechSyllabusAsync()
            Log.e(TAG, "getSyllabus: working", )
            try {
                Log.e(TAG, "getSyllabus: try", )
                val syllabusObject = getSyllabusDeferred.await()
                Log.e(TAG, "getSyllabus: working2",)

                Log.e(TAG, "getSyllabus: ${syllabusObject.toString()}", )
                if (syllabusObject.results.isNotEmpty()){
                    _syllabus.value = syllabusObject.results
                }
                Log.e(TAG, "getSyllabus: working2",)
            }
            catch (t: Throwable){
                Log.e(TAG, "getSyllabus: error", )
            }
        }
    }
}