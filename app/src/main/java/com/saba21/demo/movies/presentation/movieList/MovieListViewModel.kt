package com.saba21.demo.movies.presentation.movieList

import com.saba21.demo.domain.useCase.GetTopRatedMoviesUseCase
import com.saba21.demo.movies.base.di.scopes.FragmentScope
import com.saba21.demo.movies.base.fragment.viewModel.BaseViewModel
import io.reactivex.Observable
import javax.inject.Inject

@FragmentScope
class MovieListViewModel
@Inject constructor(
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
) : BaseViewModel<MovieListActions, MovieListViewState>() {

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
