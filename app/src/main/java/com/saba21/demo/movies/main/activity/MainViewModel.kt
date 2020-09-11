package com.saba21.demo.movies.main.activity

import androidx.lifecycle.ViewModel
import com.saba21.demo.movies.base.presentation.abstractHandlers.alert.BaseAlert
import com.saba21.demo.movies.base.presentation.abstractHandlers.alert.IntermediaryAlertHandler
import com.saba21.demo.movies.base.presentation.abstractHandlers.error.BaseError
import com.saba21.demo.movies.base.presentation.abstractHandlers.error.CommonErrors
import com.saba21.demo.movies.base.presentation.abstractHandlers.error.IntermediaryErrorHandler
import com.saba21.demo.movies.base.presentation.abstractHandlers.loader.BaseLoader
import com.saba21.demo.movies.base.presentation.abstractHandlers.loader.IntermediaryLoaderHandler
import com.saba21.demo.movies.base.presentation.abstractHandlers.navigation.BaseNavigation
import com.saba21.demo.movies.base.presentation.abstractHandlers.navigation.IntermediaryNavigationHandler
import com.saba21.demo.movies.main.activity.handlers.MainAlertHandler
import com.saba21.demo.movies.main.activity.handlers.MainLoaderHandler
import com.saba21.demo.movies.main.activity.handlers.MainNavigationHandler
import com.saba21.demo.movies.presentation.movieDetails.MovieDetailsActions
import com.saba21.demo.movies.presentation.movieList.MovieListActions

class MainViewModel(
    private val navigationHandler: MainNavigationHandler,
    private val alertHandler: MainAlertHandler,
    private val mainLoaderHandler: MainLoaderHandler
) : ViewModel(),
    IntermediaryNavigationHandler,
    IntermediaryErrorHandler,
    IntermediaryLoaderHandler,
    IntermediaryAlertHandler {

    override fun handleNavigation(navigation: BaseNavigation) {
        when (navigation) {
            is MovieListActions.Navigation.GoToDetails ->
                navigationHandler.goToMovieDetails(navigation.movieItem)
            is MovieDetailsActions.Navigation.GoBack ->
                navigationHandler.onBackPressed()
        }
    }

    override fun handleError(error: BaseError) {
        when (error) {
            is CommonErrors -> {
                alertHandler.showAlert(error.messageRes)
            }
        }
    }

    override fun handleLoader(item: BaseLoader) {
        if (item.visible)
            mainLoaderHandler.showLoader()
        else
            mainLoaderHandler.hideLoader()
    }

    override fun <T> handleAlert(item: BaseAlert<T>, callback: (T) -> Unit) {
        alertHandler.showAlertForResult(android.R.string.untitled) {
            callback.invoke(item.nextAction)
        }
    }

}