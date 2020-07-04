package com.saba21.demo.movies.presentation.movieList

import com.saba21.demo.movies.base.presentation.action.BaseAction
import com.saba21.demo.movies.base.presentation.navigationHandling.BaseNavigation

sealed class MovieListActions : BaseAction {

    sealed class Navigation : MovieListActions(), BaseNavigation {
        object GoToDetails : Navigation()
    }

}