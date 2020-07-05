package com.saba21.demo.movies.presentation.movieList

import android.view.View
import com.saba21.demo.movies.R
import com.saba21.demo.movies.base.fragment.BaseFragment
import com.saba21.demo.movies.main.activity.di.ActivityComponent
import com.saba21.demo.movies.presentation.movieList.di.MovieListComponent

class MovieListFragment : BaseFragment<MovieListActions, MovieListViewState, MovieListViewModel>(
    R.layout.fragment_movie_list,
    MovieListViewModel::class
) {

    override fun getComponent(activityComponent: ActivityComponent): MovieListComponent {
        return activityComponent.getMovieListComponentFactory().create(this)
    }

    override fun onDraw(view: View) {
        super.onDraw(view)
        postAction(MovieListActions.LoadTopRatedMoviesPage(1))
        postAction(MovieListActions.LoadPopularMoviesPage(1))
    }

    override fun reflectState(state: MovieListViewState) {

    }

    override fun restoreState(lastState: MovieListViewState) {

    }

}