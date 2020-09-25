package com.saba21.demo.movies.presentation.movieList

import com.saba21.demo.domain.models.MovieModel
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.navigation.BaseNavigation
import com.saba21.demo.movies.base.presentation.action.BaseAction

sealed class MovieListActions : BaseAction {

    sealed class Navigation : MovieListActions(), BaseNavigation {
        class GoToDetails(val movieItem: MovieModel) : Navigation()
    }

    object LoadTopRatedMoviesPage : MovieListActions()
}

























