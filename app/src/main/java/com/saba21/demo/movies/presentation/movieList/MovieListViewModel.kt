package com.saba21.demo.movies.presentation.movieList

import com.saba21.demo.domain.useCase.GetPopularMoviesUseCase
import com.saba21.demo.domain.useCase.GetTopRatedMoviesUseCase
import com.saba21.demo.movies.base.viewModel.BaseViewModel
import io.reactivex.Observable
import javax.inject.Inject

class MovieListViewModel
@Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
) : BaseViewModel<MovieListActions, MovieListViewState>() {

    override fun onActionReceived(action: MovieListActions): Observable<MovieListViewState> {
        return when (action) {
            is MovieListActions.LoadPopularMoviesPage -> {
                getPopularMoviesUseCase.create(action.page)
                    .map {
                        MovieListViewState.DrawPopularMovies(action.page, it)
                    }
            }
            is MovieListActions.LoadTopRatedMoviesPage -> {
                getTopRatedMoviesUseCase.create(action.page)
                    .map {
                        MovieListViewState.DrawTopRatedMovies(action.page, it)
                    }
            }
            else -> super.onActionReceived(action)
        }
    }

    override fun setInitialState(): MovieListViewState {
        return MovieListViewState.Initial
    }

}