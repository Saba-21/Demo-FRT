package com.saba21.demo.movies.main.activity

import androidx.lifecycle.ViewModel
import com.saba21.demo.movies.base.presentation.errorHandling.BaseError
import com.saba21.demo.movies.base.presentation.errorHandling.CommonErrors
import com.saba21.demo.movies.base.presentation.errorHandling.IntermediaryErrorHandler
import com.saba21.demo.movies.base.presentation.navigationHandling.BaseNavigation
import com.saba21.demo.movies.base.presentation.navigationHandling.IntermediaryNavigationHandler
import com.saba21.demo.movies.base.presentation.utilityHandling.BaseLoader
import com.saba21.demo.movies.base.presentation.utilityHandling.IntermediaryUtilityHandler
import com.saba21.demo.movies.main.activity.handlers.MainAlertHandler
import com.saba21.demo.movies.main.activity.handlers.MainLoaderHandler
import com.saba21.demo.movies.main.activity.handlers.MainNavigationHandler
import com.saba21.demo.movies.presentation.movieDetails.MovieDetailsActions
import com.saba21.demo.movies.presentation.movieList.MovieListActions
import com.saba21.demo.movies.presentation.splash.SplashActions

class MainViewModel(
    private val navigationHandler: MainNavigationHandler,
    private val alertHandler: MainAlertHandler,
    private val mainLoaderHandler: MainLoaderHandler
) : ViewModel(),
    IntermediaryNavigationHandler,
    IntermediaryErrorHandler,
    IntermediaryUtilityHandler {

    override fun handleNavigation(navigation: BaseNavigation) {
        when (navigation) {
            is SplashActions.Navigation.GoToMainScreen ->
                navigationHandler.goToMovieList()
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

}