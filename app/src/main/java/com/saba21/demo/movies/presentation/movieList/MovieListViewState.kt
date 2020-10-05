package com.saba21.demo.movies.presentation.movieList

import com.saba21.demo.domain.models.MovieModel
import com.saba21.demo.movies.base.presentation.state.BaseViewState
import com.saba21.demo.movies.base.presentation.state.BaseViewStateData

sealed class MovieListViewState :
    BaseViewState<MovieListViewState.MovieListViewData>(MovieListViewData()) {

    class DrawTopRatedMovies(val movies: List<MovieModel>) : MovieListViewState() {
        override fun stateReducer(previousData: MovieListViewData): MovieListViewData {
            return previousData.copy(topRatedMovies = movies)
        }
    }

    data class MovieListViewData(
        val topRatedMovies: List<MovieModel>? = null
    ) : BaseViewStateData()
}
