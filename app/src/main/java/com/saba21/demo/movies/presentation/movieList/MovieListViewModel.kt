package com.saba21.demo.movies.presentation.movieList

import com.saba21.demo.domain.useCase.GetPopularMoviesUseCase
import com.saba21.demo.domain.useCase.GetTopRatedMoviesUseCase
import com.saba21.demo.movies.base.viewModel.BaseViewModel
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
) : BaseViewModel<MovieListActions, MovieListViewState>() {

    override fun onBindView() {
        super.onBindView()
        getPopularMoviesUseCase.create(1)
            .subscribe {

            }.addSubscription()
        getTopRatedMoviesUseCase.create(1)
            .subscribe {

            }.addSubscription()
    }

    override fun setInitialState(): MovieListViewState {
        return MovieListViewState.Initial
    }

}