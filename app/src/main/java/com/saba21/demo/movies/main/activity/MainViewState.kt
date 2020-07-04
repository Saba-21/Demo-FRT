package com.saba21.demo.movies.main.activity

sealed class MainViewState {
    sealed class Navigation : MainViewState() {
        object GoToMovieList : Navigation()
        object GoToMovieDetails : Navigation()
    }
}