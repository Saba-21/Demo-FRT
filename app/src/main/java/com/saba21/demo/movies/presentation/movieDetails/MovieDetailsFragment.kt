package com.saba21.demo.movies.presentation.movieDetails

import android.view.View
import com.saba21.demo.movies.R
import com.saba21.demo.movies.base.fragment.BaseFragment
import com.saba21.demo.movies.main.activity.di.ActivityComponent
import com.saba21.demo.movies.presentation.movieDetails.di.MovieDetailsComponent

class MovieDetailsFragment : BaseFragment<MovieDetailsActions, MovieDetailsViewModel>(
    R.layout.fragment_movie_details,
    MovieDetailsViewModel::class
) {

    override fun getComponent(activityComponent: ActivityComponent): MovieDetailsComponent {
        return activityComponent.getMovieDetailsComponentFactory().create(this)
    }

    override fun onDraw(view: View) {

    }

}