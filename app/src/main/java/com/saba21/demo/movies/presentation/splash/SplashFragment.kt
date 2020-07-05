package com.saba21.demo.movies.presentation.splash

import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.END
import androidx.constraintlayout.widget.ConstraintSet.START
import com.saba21.demo.movies.R
import com.saba21.demo.movies.base.fragment.BaseFragment
import com.saba21.demo.movies.main.activity.di.ActivityComponent
import com.saba21.demo.movies.presentation.splash.di.SplashComponent
import kotlinx.android.synthetic.main.fragment_splash.*

class SplashFragment : BaseFragment<SplashActions, SplashViewState, SplashViewModel>(
    R.layout.fragment_splash,
    SplashViewModel::class
) {

    override fun getComponent(activityComponent: ActivityComponent): SplashComponent {
        return activityComponent.getSplashComponentFactory().create(this)
    }

    override fun onDraw(view: View, lastState: SplashViewState?) {
        super.onDraw(view, lastState)
        startSplashScreenAnimation()
    }

    private fun startSplashScreenAnimation() {
        layoutSplash.post {
            with(ConstraintSet()) {
                clone(layoutSplash)
                connect(tvTitleFirst.id, START, layoutSplash.id, START)
                connect(tvTitleFirst.id, END, layoutSplash.id, END)
                connect(tvTitleSecond.id, START, layoutSplash.id, START)
                connect(tvTitleSecond.id, END, layoutSplash.id, END)
                val transition = AutoTransition()
                transition.duration = 1000
                TransitionManager.beginDelayedTransition(layoutSplash, transition)
                applyTo(layoutSplash)
            }
        }
    }

}