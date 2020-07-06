package com.saba21.demo.movies.main.activity

import com.saba21.demo.domain.models.MovieModel
import com.saba21.demo.movies.base.presentation.errorHandling.CommonErrors

sealed class MainViewState {
    sealed class Navigation : MainViewState() {
        object GoToMovieList : Navigation()
        class GoToMovieDetails(val movieModel: MovieModel) : Navigation()
        object GoBack : Navigation()
    }

    sealed class Error : MainViewState() {
        class CommonError(val type: CommonErrors) : Error()
    }
}