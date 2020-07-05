package com.saba21.demo.movies.presentation.movieDetails

import com.saba21.demo.domain.models.MovieModel
import com.saba21.demo.movies.base.presentation.state.BaseViewState
import com.saba21.demo.movies.base.presentation.state.BaseViewStateData

sealed class MovieDetailsViewState(
    override val stateReducer: ((MovieDetailsViewData) -> MovieDetailsViewData) = { it }
) : BaseViewState<MovieDetailsViewState.MovieDetailsViewData>(MovieDetailsViewData()) {

    object Initial : MovieDetailsViewState()

    class DrawMovieDetails(val movieModel: MovieModel) : MovieDetailsViewState(
        stateReducer = {
            it.copy(movieModel = movieModel)
        }
    )

    data class MovieDetailsViewData(val movieModel: MovieModel? = null) : BaseViewStateData()

}