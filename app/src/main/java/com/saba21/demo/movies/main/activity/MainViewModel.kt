package com.saba21.demo.movies.main.activity

import com.saba21.demo.movies.base.activity.viewModel.BaseViewModel
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.alert.AlertCommand
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.error.CommonErrors
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.error.ErrorCommand
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.loader.LoaderCommand
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.navigation.NavigationCommand
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.permission.PermissionCommand
import com.saba21.demo.movies.base.di.scopes.ActivityScope
import com.saba21.demo.movies.presentation.movieDetails.MovieDetailsActions
import com.saba21.demo.movies.presentation.movieList.MovieListActions
import javax.inject.Inject

@ActivityScope
class MainViewModel @Inject constructor() : BaseViewModel() {

    override fun handleNavigationCommand(navigationCommand: NavigationCommand) {
        requireHandler {
            when (navigationCommand) {
                is MovieListActions.Navigation.GoToDetails ->
                    it.goToMovieDetails(navigationCommand.movieItem)
                is MovieDetailsActions.Navigation.GoBack ->
                    it.popBackStack()
            }
        }
    }

    override fun handleErrorCommand(errorCommand: ErrorCommand) {
        requireHandler {
            when (errorCommand) {
                is CommonErrors -> {
                    it.showAlert(errorCommand.messageRes)
                }
            }
        }
    }

    override fun handleLoaderCommand(item: LoaderCommand) {
        requireHandler {
            if (item.visible)
                it.showLoader()
            else
                it.hideLoader()
        }
    }

    override fun <T> handleAlertCommand(item: AlertCommand<T>, callback: (T) -> Unit) {
        requireHandler {
            it.showAlertForResult(android.R.string.untitled) {
                callback.invoke(item.pendingAction)
            }
        }
    }

    override fun <T> handlePermissionCommand(item: PermissionCommand<T>, callback: (T) -> Unit) {
        requireHandler {
            it.getPermission(item.key) { result ->
                val action = if (result)
                    item.pendingPositiveAction
                else
                    item.pendingNegativeAction
                callback.invoke(action)
            }
        }
    }
}
