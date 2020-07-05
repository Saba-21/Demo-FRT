package com.saba21.demo.movies.presentation.splash

import com.saba21.demo.movies.base.viewModel.BaseViewModel
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashViewModel @Inject constructor() : BaseViewModel<SplashActions, SplashViewState>() {

    override val initialViewState: SplashViewState = SplashViewState.Initial

    private val splashScreenTimeOut = 1500L

    override fun onBindView(initial: Boolean) {
        Observable.just(SplashActions.Navigation.GoToMainScreen)
            .delay(splashScreenTimeOut, TimeUnit.MILLISECONDS)
            .subscribe(this::postAction)
            .addSubscription()
    }

}