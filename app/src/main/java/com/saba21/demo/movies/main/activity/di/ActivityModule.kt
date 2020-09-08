package com.saba21.demo.movies.main.activity.di

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.saba21.demo.movies.base.di.scopes.ActivityScope
import com.saba21.demo.movies.base.presentation.errorHandling.ErrorHandler
import com.saba21.demo.movies.base.presentation.navigationHandling.NavigationHandler
import com.saba21.demo.movies.main.activity.MainViewModel
import com.saba21.demo.movies.main.activity.handlers.MainNavigationHandler
import com.saba21.demo.movies.main.activity.handlers.MainUtilitiesHandler
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {

    @Provides
    @ActivityScope
    fun provideMainNavigationHandler(activity: AppCompatActivity): MainNavigationHandler {
        return MainNavigationHandler(
            activity.supportFragmentManager
        )
    }

    @Provides
    @ActivityScope
    fun provideMainUtilityHandler(activity: AppCompatActivity): MainUtilitiesHandler {
        return MainUtilitiesHandler(
            activity
        )
    }

    @Provides
    @ActivityScope
    fun provideViewModel(
        activity: AppCompatActivity,
        viewModelFactory: MainViewModelFactory
    ): MainViewModel {
        return activity.viewModels<MainViewModel> {
            viewModelFactory
        }.value
    }

    @Provides
    @ActivityScope
    fun provideNavigationHandler(mainViewModel: MainViewModel): NavigationHandler = mainViewModel

    @Provides
    @ActivityScope
    fun provideErrorHandler(mainViewModel: MainViewModel): ErrorHandler = mainViewModel

}