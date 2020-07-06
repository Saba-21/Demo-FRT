package com.saba21.demo.movies.presentation.movieList

import com.saba21.demo.domain.models.MovieModel
import com.saba21.demo.movies.base.presentation.state.BaseViewState
import com.saba21.demo.movies.base.presentation.state.BaseViewStateData

sealed class MovieListViewState(
    override val stateReducer: ((MovieListViewData) -> MovieListViewData) = { it }
) : BaseViewState<MovieListViewState.MovieListViewData>(MovieListViewData()) {

    object Initial : MovieListViewState()

    class DrawPopularMovies(
        val pageIndex: Int,
        val movies: List<MovieModel>
    ) : MovieListViewState(stateReducer = {
        it.copy(
            popularMoviesPageIndex = pageIndex,
            popularMovies = movies
        )
    })

    class DrawTopRatedMovies(
        val pageIndex: Int,
        val movies: List<MovieModel>
    ) : MovieListViewState(stateReducer = {
        it.copy(
            topRatedMoviesPageIndex = pageIndex,
            topRatedMovies = movies
        )
    })

    class DrawFavoriteMovies(
        val movies: List<MovieModel>
    ) : MovieListViewState(stateReducer = {
        it.copy(
            favoriteMovies = movies
        )
    })

    data class MovieListViewData(
        val topRatedMoviesPageIndex: Int? = null,
        val popularMoviesPageIndex: Int? = null,
        val topRatedMovies: List<MovieModel>? = null,
        val popularMovies: List<MovieModel>? = null,
        val favoriteMovies: List<MovieModel>? = null
    ) : BaseViewStateData()

}