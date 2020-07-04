package com.saba21.demo.movies.presentation.movieList

import com.saba21.demo.movies.base.viewModel.BaseViewModel
import io.reactivex.Observable
import javax.inject.Inject

class MovieListViewModel @Inject constructor() :
    BaseViewModel<MovieListActions, MovieListViewState>() {

    override fun setInitialState(): MovieListViewState {
        return MovieListViewState.Initial
    }

}