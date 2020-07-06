package com.saba21.demo.movies.presentation.movieDetails

import com.saba21.demo.domain.useCase.CheckFavoriteMoviesUseCase
import com.saba21.demo.domain.useCase.SaveFavoriteMoviesUseCase
import com.saba21.demo.movies.base.viewModel.BaseViewModel
import com.saba21.demo.movies.presentation.movieDetails.util.MovieDetailsParams
import io.reactivex.Observable
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val parameters: MovieDetailsParams,
    private val saveFavoriteMoviesUseCase: SaveFavoriteMoviesUseCase,
    private val checkFavoriteMoviesUseCase: CheckFavoriteMoviesUseCase
) : BaseViewModel<MovieDetailsActions, MovieDetailsViewState>() {

    override val initialViewState: MovieDetailsViewState = MovieDetailsViewState.Initial

    override fun onBindView(initial: Boolean) {
        postState(MovieDetailsViewState.DrawMovieDetails(parameters.movieModel))
        postAction(MovieDetailsActions.CheckFavorite)
    }

    override fun onActionReceived(action: MovieDetailsActions): Observable<MovieDetailsViewState> {
        return when (action) {
            is MovieDetailsActions.SaveFavorite -> saveFavoriteMoviesUseCase.create(parameters.movieModel)
                .map {
                    MovieDetailsViewState.MakeMovieFavorite(true)
                }
            is MovieDetailsActions.CheckFavorite -> checkFavoriteMoviesUseCase.create(parameters.movieModel.id)
                .map {
                    MovieDetailsViewState.MakeMovieFavorite(it)
                }
            else -> super.onActionReceived(action)
        }
    }

}