package com.saba21.demo.movies.presentation.movieDetails

import com.saba21.demo.movies.base.presentation.action.BaseAction
import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.navigation.BaseNavigation

sealed class MovieDetailsActions : BaseAction {

    sealed class Navigation : MovieDetailsActions(), BaseNavigation {
        object GoBack : Navigation()
    }

    object CheckFavorite : MovieDetailsActions()

    object SaveFavorite : MovieDetailsActions()

}