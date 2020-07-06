package com.saba21.demo.movies.main.activity

import androidx.lifecycle.ViewModel
import com.saba21.demo.movies.base.presentation.errorHandling.BaseError
import com.saba21.demo.movies.base.presentation.errorHandling.ErrorHandler
import com.saba21.demo.movies.base.presentation.errorHandling.CommonErrors
import com.saba21.demo.movies.base.presentation.navigationHandling.BaseNavigation
import com.saba21.demo.movies.base.presentation.navigationHandling.NavigationHandler
import com.saba21.demo.movies.presentation.movieDetails.MovieDetailsActions
import com.saba21.demo.movies.presentation.movieList.MovieListActions
import com.saba21.demo.movies.presentation.splash.SplashActions
import io.reactivex.subjects.PublishSubject

class MainViewModel : ViewModel(),
    NavigationHandler,
    ErrorHandler {

    val mainViewState = PublishSubject.create<MainViewState>()

    override fun handleNavigation(navigation: BaseNavigation) {
        when (navigation) {
            is SplashActions.Navigation.GoToMainScreen ->
                mainViewState.onNext(MainViewState.Navigation.GoToMovieList)
            is MovieListActions.Navigation.GoToDetails ->
                mainViewState.onNext(MainViewState.Navigation.GoToMovieDetails(navigation.movieItem))
            is MovieDetailsActions.Navigation.GoBack ->
                mainViewState.onNext(MainViewState.Navigation.GoBack)
        }
    }

    override fun handleError(error: BaseError) {
        when (error) {
            is CommonErrors -> {
                mainViewState.onNext(MainViewState.Error.CommonError(error))
            }
        }
    }

}