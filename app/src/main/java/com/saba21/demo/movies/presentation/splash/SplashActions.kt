package com.saba21.demo.movies.presentation.splash

import com.saba21.demo.movies.base.presentation.action.BaseAction
import com.saba21.demo.movies.base.presentation.navigationHandling.BaseNavigation

sealed class SplashActions : BaseAction {

    sealed class Navigation : SplashActions(), BaseNavigation {
        object GoToMainScreen : Navigation()
    }

}