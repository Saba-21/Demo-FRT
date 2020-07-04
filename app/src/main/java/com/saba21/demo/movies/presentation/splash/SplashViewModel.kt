package com.saba21.demo.movies.presentation.splash

import com.saba21.demo.movies.base.viewModel.BaseViewModel
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashViewModel @Inject constructor() : BaseViewModel<SplashActions, SplashViewState>() {

    override fun onBindView() {
        Observable.just(SplashActions.Navigation.GoToMainScreen)
            .delay(1500, TimeUnit.MILLISECONDS)
            .subscribe(this::postAction)
            .addSubscription()
    }

    override fun setInitialState(): SplashViewState {
        return SplashViewState.Initial
    }

}