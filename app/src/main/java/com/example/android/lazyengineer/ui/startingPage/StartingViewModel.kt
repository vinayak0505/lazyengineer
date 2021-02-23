package com.example.android.lazyengineer.ui.startingPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StartingViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val _navigateToContent = MutableLiveData<String>()
    val navigateToContent: LiveData<String>
        get() = _navigateToContent

}