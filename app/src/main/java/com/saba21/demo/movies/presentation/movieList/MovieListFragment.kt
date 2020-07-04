package com.saba21.demo.movies.presentation.movieList

import android.view.View
import com.saba21.demo.movies.R
import com.saba21.demo.movies.base.fragment.BaseFragment
import com.saba21.demo.movies.main.activity.di.ActivityComponent
import com.saba21.demo.movies.presentation.movieList.di.MovieListComponent
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieListFragment : BaseFragment<MovieListActions, MovieListViewModel>(
    R.layout.fragment_movie_list,
    MovieListViewModel::class
) {

    override fun getComponent(activityComponent: ActivityComponent): MovieListComponent {
        return activityComponent.getMovieListComponentFactory().create(this)
    }

    override fun onDraw(view: View) {
        layoutMovieList.setOnClickListener {
            postAction(MovieListActions.Navigation.GoToDetails)
        }
    }

}