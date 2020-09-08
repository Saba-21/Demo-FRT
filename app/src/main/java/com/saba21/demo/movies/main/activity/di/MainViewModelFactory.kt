package com.saba21.demo.movies.main.activity.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saba21.demo.movies.main.activity.MainViewModel
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val navigationHandler: MainNavigationHandler,
    private val utilityHandler: MainUtilitiesHandler
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return MainViewModel(navigationHandler, utilityHandler) as T
    }
}