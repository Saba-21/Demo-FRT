package com.saba21.demo.movies.presentation.movieDetails

import com.saba21.demo.movies.base.presentation.state.BaseViewState
import com.saba21.demo.movies.base.presentation.state.BaseViewStateData

sealed class MovieDetailsViewState(override val currentState: MovieDetailsViewData) :
    BaseViewState<MovieDetailsViewState.MovieDetailsViewData> {

    object Initial : MovieDetailsViewState(MovieDetailsViewData())

    class MovieDetailsViewData : BaseViewStateData

}