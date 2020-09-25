package com.saba21.demo.movies.presentation.movieList

import com.saba21.demo.domain.models.MovieModel
import com.saba21.demo.movies.base.presentation.state.BaseViewState
import com.saba21.demo.movies.base.presentation.state.BaseViewStateData

sealed class MovieListViewState(
    override val stateReducer: ((MovieListViewData) -> MovieListViewData) = { it }
) : BaseViewState<MovieListViewState.MovieListViewData>(MovieListViewData()) {

    object Initial : MovieListViewState()

    class DrawTopRatedMovies(
        val movies: List<MovieModel>
    ) : MovieListViewState(stateReducer = {
        it.copy(topRatedMovies = movies)
    })

    data class MovieListViewData(
        val topRatedMovies: List<MovieModel>? = null
    ) : BaseViewStateData()
}
