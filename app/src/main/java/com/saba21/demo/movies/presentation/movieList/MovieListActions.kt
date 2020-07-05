package com.saba21.demo.movies.presentation.movieList

import com.saba21.demo.domain.models.MovieModel
import com.saba21.demo.movies.base.presentation.action.BaseAction
import com.saba21.demo.movies.base.presentation.navigationHandling.BaseNavigation

sealed class MovieListActions : BaseAction {

    sealed class Navigation : MovieListActions(), BaseNavigation {
        class GoToDetails(val movieItem: MovieModel) : Navigation()
    }

    class LoadTopRatedMoviesPage(val page: Int) : MovieListActions()

    class LoadPopularMoviesPage(val page: Int) : MovieListActions()

}