package com.saba21.demo.movies.main.activity.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saba21.demo.movies.main.activity.MainViewModel

class MainViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel() as T
    }
}