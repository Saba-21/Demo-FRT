package com.saba21.demo.movies.main.activity.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saba21.demo.movies.base.di.scopes.ActivityScope
import com.saba21.demo.movies.main.activity.MainViewModel
import javax.inject.Inject
import javax.inject.Provider

@ActivityScope
class MainViewModelFactory
@Inject constructor(
    private val viewModelProvider: Provider<MainViewModel>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return viewModelProvider.get() as T
    }
}
