package com.saba21.demo.movies.presentation.splash

import android.view.View
import com.saba21.demo.movies.R
import com.saba21.demo.movies.app.activity.MainActivity
import com.saba21.demo.movies.base.fragment.BaseFragment

class SplashFragment :
    BaseFragment<SplashViewModel>(R.layout.fragment_splash, SplashViewModel::class) {

    override val component by lazy {
        (activity as MainActivity).activityComponent
            .getSplashComponentFactory()
            .create(this)
    }

    override fun onDraw(view: View) {

    }

}