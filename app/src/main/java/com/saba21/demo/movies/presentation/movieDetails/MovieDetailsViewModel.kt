package com.saba21.demo.movies.presentation.movieDetails

import com.saba21.demo.movies.base.viewModel.BaseViewModel
import com.saba21.demo.movies.presentation.movieDetails.util.MovieDetailsParams
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val parameters: MovieDetailsParams
) : BaseViewModel<MovieDetailsActions, MovieDetailsViewState>() {

    override val initialViewState: MovieDetailsViewState = MovieDetailsViewState.Initial

    override fun onBindView(initial: Boolean) {
        postState(MovieDetailsViewState.DrawMovieDetails(parameters.movieModel))
    }

}