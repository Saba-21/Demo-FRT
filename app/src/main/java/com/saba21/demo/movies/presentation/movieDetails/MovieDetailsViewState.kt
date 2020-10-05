package com.saba21.demo.movies.presentation.movieDetails

import com.saba21.demo.domain.models.MovieModel
import com.saba21.demo.movies.base.presentation.state.BaseViewState
import com.saba21.demo.movies.base.presentation.state.BaseViewStateData

sealed class MovieDetailsViewState :
    BaseViewState<MovieDetailsViewState.MovieDetailsViewData>(MovieDetailsViewData()) {

    class MakeMovieFavorite(val isFavorite: Boolean) : MovieDetailsViewState() {
        override fun stateReducer(previousData: MovieDetailsViewData): MovieDetailsViewData {
            return previousData.copy(isFavorite = isFavorite)
        }
    }

    class DrawMovieDetails(val movieModel: MovieModel) : MovieDetailsViewState() {
        override fun stateReducer(previousData: MovieDetailsViewData): MovieDetailsViewData {
            return previousData.copy(movieModel = movieModel)
        }
    }

    data class MovieDetailsViewData(
        val movieModel: MovieModel? = null,
        val isFavorite: Boolean? = null
    ) : BaseViewStateData()

}
