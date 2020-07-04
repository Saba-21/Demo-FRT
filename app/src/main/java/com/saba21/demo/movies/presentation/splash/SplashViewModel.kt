package com.saba21.demo.movies.presentation.splash

import com.saba21.demo.movies.base.viewModel.BaseViewModel
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashViewModel @Inject constructor() : BaseViewModel<SplashActions>() {

    override fun bindView() {
        Observable.just(SplashActions.Navigation.GoToMainScreen)
            .delay(1500, TimeUnit.MILLISECONDS)
            .subscribe(this::postAction)
            .addSubscription()
    }

    override fun onActionReceived(action: SplashActions): Observable<Unit> {
        return super.onActionReceived(action)
    }

}