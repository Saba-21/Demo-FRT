package com.saba21.demo.movies.presentation.splash

import com.saba21.demo.movies.base.presentation.state.BaseViewState
import com.saba21.demo.movies.base.presentation.state.BaseViewStateData

sealed class SplashViewState(
    override val stateReducer: ((SplashViewData) -> SplashViewData) = { it }
) : BaseViewState<SplashViewState.SplashViewData>(SplashViewData()) {

    object Initial : SplashViewState()

    class SplashViewData : BaseViewStateData()

}