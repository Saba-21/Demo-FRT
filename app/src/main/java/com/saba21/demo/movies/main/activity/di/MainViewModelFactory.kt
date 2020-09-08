package com.saba21.demo.movies.main.activity.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saba21.demo.movies.main.activity.MainViewModel
import com.saba21.demo.movies.main.activity.handlers.MainAlertHandler
import com.saba21.demo.movies.main.activity.handlers.MainLoaderHandler
import com.saba21.demo.movies.main.activity.handlers.MainNavigationHandler
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val navigationHandler: MainNavigationHandler,
    private val alertHandler: MainAlertHandler,
    private val loaderHandler: MainLoaderHandler
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return MainViewModel(navigationHandler, alertHandler, loaderHandler) as T
    }
}