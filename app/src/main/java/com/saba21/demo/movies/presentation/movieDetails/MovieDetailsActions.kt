package com.saba21.demo.movies.presentation.movieDetails

import com.saba21.demo.movies.base.activity.viewModel.abstractHandlers.navigation.NavigationCommand
import com.saba21.demo.movies.base.presentation.action.BaseAction

sealed class MovieDetailsActions : BaseAction {

    sealed class Navigation : MovieDetailsActions(), NavigationCommand {
        object GoBack : Navigation()
    }

    object CheckFavorite : MovieDetailsActions()

    object SaveFavorite : MovieDetailsActions()
}
