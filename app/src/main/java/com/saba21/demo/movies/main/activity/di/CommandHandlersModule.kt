package com.saba21.demo.movies.main.activity.di

import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.alert.AlertCommandHandler
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.error.ErrorCommandHandler
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.loader.LoaderCommandHandler
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.navigation.NavigationCommandHandler
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.permission.PermissionCommandHandler
import com.saba21.demo.movies.base.di.scopes.ActivityScope
import com.saba21.demo.movies.main.activity.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class CommandHandlersModule {

    @Provides
    @ActivityScope
    fun provideNavigationHandler(mainViewModel: MainViewModel):
            NavigationCommandHandler = mainViewModel

    @Provides
    @ActivityScope
    fun provideErrorHandler(mainViewModel: MainViewModel):
            ErrorCommandHandler = mainViewModel

    @Provides
    @ActivityScope
    fun provideLoaderHandler(mainViewModel: MainViewModel):
            LoaderCommandHandler = mainViewModel

    @Provides
    @ActivityScope
    fun provideAlertHandler(mainViewModel: MainViewModel):
            AlertCommandHandler = mainViewModel

    @Provides
    @ActivityScope
    fun providePermissionHandler(mainViewModel: MainViewModel):
            PermissionCommandHandler = mainViewModel
}
