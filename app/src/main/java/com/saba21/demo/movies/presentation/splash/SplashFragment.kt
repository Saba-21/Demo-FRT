package com.saba21.demo.movies.presentation.splash

import com.saba21.demo.movies.R
import com.saba21.demo.movies.base.fragment.BaseFragment
import com.saba21.demo.movies.main.activity.di.ActivityComponent
import com.saba21.demo.movies.presentation.splash.di.SplashComponent

class SplashFragment : BaseFragment<SplashActions, SplashViewState, SplashViewModel>(
    R.layout.fragment_splash,
    SplashViewModel::class
) {

    override fun getComponent(activityComponent: ActivityComponent): SplashComponent {
        return activityComponent.getSplashComponentFactory().create(this)
    }

}