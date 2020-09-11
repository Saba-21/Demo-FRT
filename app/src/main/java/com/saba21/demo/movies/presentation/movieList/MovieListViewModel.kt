package com.saba21.demo.movies.presentation.movieList

import com.saba21.demo.domain.useCase.GetTopRatedMoviesUseCase
import com.saba21.demo.movies.base.viewModel.BaseViewModel
import io.reactivex.Observable
import javax.inject.Inject

class MovieListViewModel
@Inject constructor(
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
) : BaseViewModel<MovieListActions, MovieListViewState>() {

    override val initialViewState: MovieListViewState = MovieListViewState.Initial

    override fun onBindView(initial: Boolean) {
        if (initial)
            postAction(MovieListActions.LoadTopRatedMoviesPage)
    }

    override fun onActionReceived(action: MovieListActions): Observable<MovieListViewState> {
        return when (action) {
            is MovieListActions.LoadTopRatedMoviesPage -> {
                getTopRatedMoviesUseCase.create(1)
                    .map {
                        MovieListViewState.DrawTopRatedMovies(it)
                    }
            }
            else -> super.onActionReceived(action)
        }
    }

}