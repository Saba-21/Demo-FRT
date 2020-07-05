package com.saba21.demo.movies.main.activity

import com.saba21.demo.domain.models.MovieModel

sealed class MainViewState {
    sealed class Navigation : MainViewState() {
        object GoToMovieList : Navigation()
        class GoToMovieDetails(val movieModel: MovieModel) : Navigation()
        object GoBack : Navigation()
    }
}