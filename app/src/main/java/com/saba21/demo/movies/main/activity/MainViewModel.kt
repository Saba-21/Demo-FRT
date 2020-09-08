package com.saba21.demo.movies.main.activity

import androidx.lifecycle.ViewModel
import com.saba21.demo.movies.base.presentation.errorHandling.BaseError
import com.saba21.demo.movies.base.presentation.errorHandling.CommonErrors
import com.saba21.demo.movies.base.presentation.errorHandling.ErrorHandler
import com.saba21.demo.movies.base.presentation.navigationHandling.BaseNavigation
import com.saba21.demo.movies.base.presentation.navigationHandling.NavigationHandler
import com.saba21.demo.movies.main.activity.handlers.MainNavigationHandler
import com.saba21.demo.movies.main.activity.handlers.MainUtilitiesHandler
import com.saba21.demo.movies.presentation.movieDetails.MovieDetailsActions
import com.saba21.demo.movies.presentation.movieList.MovieListActions
import com.saba21.demo.movies.presentation.splash.SplashActions

class MainViewModel(
    private val navigationHandler: MainNavigationHandler,
    private val utilityHandler: MainUtilitiesHandler
) : ViewModel(),
    NavigationHandler,
    ErrorHandler {

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
                utilityHandler.showAlert(error.messageRes)
            }
        }
    }

}