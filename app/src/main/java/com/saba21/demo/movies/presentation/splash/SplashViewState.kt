package com.saba21.demo.movies.presentation.splash

import com.saba21.demo.movies.base.presentation.state.BaseViewState
import com.saba21.demo.movies.base.presentation.state.BaseViewStateData

sealed class SplashViewState(override val currentState: SplashViewData) :
    BaseViewState<SplashViewState.SplashViewData> {

    object Initial : SplashViewState(SplashViewData())

    class SplashViewData : BaseViewStateData

}