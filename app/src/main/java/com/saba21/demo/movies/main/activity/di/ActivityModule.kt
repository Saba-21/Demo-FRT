package com.saba21.demo.movies.main.activity.di

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.saba21.demo.movies.base.di.scopes.ActivityScope
import com.saba21.demo.movies.base.presentation.eventHandling.EventHandler
import com.saba21.demo.movies.base.presentation.errorHandling.ErrorHandler
import com.saba21.demo.movies.base.presentation.navigationHandling.NavigationHandler
import com.saba21.demo.movies.main.activity.MainViewModel
import com.saba21.demo.movies.main.activity.util.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {

    @Provides
    @ActivityScope
    fun provideViewModelStore(activity: AppCompatActivity): MainViewModel {
        return activity.viewModels<MainViewModel> {
            MainViewModelFactory()
        }.value
    }

    @Provides
    @ActivityScope
    fun provideNavigationHandler(mainViewModel: MainViewModel): NavigationHandler = mainViewModel

    @Provides
    @ActivityScope
    fun provideErrorHandler(mainViewModel: MainViewModel): ErrorHandler = mainViewModel

    @Provides
    @ActivityScope
    fun provideActionHandler(mainViewModel: MainViewModel): EventHandler = mainViewModel

}