package com.example.android.lazyengineer.syllabus

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.lazyengineer.LazyEngineerApi
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

    fun getSyllabus() {
        coroutineScope.launch {
            val getSyllabusDeferred = LazyEngineerApi.retrofitService.getBTechSyllabusAsync()
            try {
                val syllabusObject = getSyllabusDeferred
                _syllabus.value = syllabusObject.body()?.results
            } catch (t: Throwable) {
            }
        }
    }
}