package com.saba21.demo.movies.presentation.splash

import com.saba21.demo.movies.base.presentation.navigationHandling.BaseNavigation

sealed class SplashViewActions {

    sealed class SplashNavigationActions : SplashViewActions(), BaseNavigation {
        object GoToMainScreen : SplashNavigationActions()
    }

}