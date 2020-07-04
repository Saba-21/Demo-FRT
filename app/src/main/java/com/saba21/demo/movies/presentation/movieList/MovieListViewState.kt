package com.saba21.demo.movies.presentation.movieList

import com.saba21.demo.movies.base.presentation.state.BaseViewState
import com.saba21.demo.movies.base.presentation.state.BaseViewStateData

sealed class MovieListViewState(override val currentState: MovieListViewData) :
    BaseViewState<MovieListViewState.MovieListViewData> {

    object Initial : MovieListViewState(MovieListViewData())

    class MovieListViewData : BaseViewStateData

}