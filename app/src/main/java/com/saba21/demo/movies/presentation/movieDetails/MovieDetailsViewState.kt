package com.saba21.demo.movies.presentation.movieDetails

import com.saba21.demo.movies.base.presentation.state.BaseViewState
import com.saba21.demo.movies.base.presentation.state.BaseViewStateData

sealed class MovieDetailsViewState(
    override val stateReducer: ((MovieDetailsViewData) -> MovieDetailsViewData) = { it }
) : BaseViewState<MovieDetailsViewState.MovieDetailsViewData>(MovieDetailsViewData()) {

    object Initial : MovieDetailsViewState()

    class MovieDetailsViewData : BaseViewStateData()

}