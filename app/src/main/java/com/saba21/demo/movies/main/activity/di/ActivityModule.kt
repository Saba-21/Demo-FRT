package com.saba21.demo.movies.main.activity.di

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.saba21.demo.movies.base.di.scopes.ActivityScope
import com.saba21.demo.movies.main.activity.MainViewModel
import com.saba21.demo.movies.main.activity.handlers.MainAlertHandler
import com.saba21.demo.movies.main.activity.handlers.MainLoaderHandler
import com.saba21.demo.movies.main.activity.handlers.MainNavigationHandler
import dagger.Module
import dagger.Provides

@Module(includes = [AbstractHandlersModule::class])
class ActivityModule {

    @Provides
    @ActivityScope
    fun provideMainNavigationHandler(activity: AppCompatActivity): MainNavigationHandler {
        return MainNavigationHandler(activity.supportFragmentManager)
    }

    @Provides
    @ActivityScope
    fun provideMainAlertHandler(activity: AppCompatActivity): MainAlertHandler {
        return MainAlertHandler(activity)
    }

    @Provides
    @ActivityScope
    fun provideMainLoaderHandler(activity: AppCompatActivity): MainLoaderHandler {
        return MainLoaderHandler(activity)
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

}