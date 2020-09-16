package com.saba21.demo.movies.main.activity.di

import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.alert.IntermediaryAlertHandler
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.error.IntermediaryErrorHandler
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.loader.IntermediaryLoaderHandler
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.navigation.IntermediaryNavigationHandler
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.permission.IntermediaryPermissionHandler
import com.saba21.demo.movies.base.di.scopes.ActivityScope
import com.saba21.demo.movies.main.activity.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class AbstractHandlersModule {

    @Provides
    @ActivityScope
    fun provideNavigationHandler(mainViewModel: MainViewModel):
            IntermediaryNavigationHandler = mainViewModel

    @Provides
    @ActivityScope
    fun provideErrorHandler(mainViewModel: MainViewModel):
            IntermediaryErrorHandler = mainViewModel

    @Provides
    @ActivityScope
    fun provideLoaderHandler(mainViewModel: MainViewModel):
            IntermediaryLoaderHandler = mainViewModel

    @Provides
    @ActivityScope
    fun provideAlertHandler(mainViewModel: MainViewModel):
            IntermediaryAlertHandler = mainViewModel

    @Provides
    @ActivityScope
    fun providePermissionHandler(mainViewModel: MainViewModel):
            IntermediaryPermissionHandler = mainViewModel

}