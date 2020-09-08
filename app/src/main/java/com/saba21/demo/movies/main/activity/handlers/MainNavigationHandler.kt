package com.saba21.demo.movies.main.activity.handlers

import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.saba21.demo.domain.models.MovieModel
import com.saba21.demo.movies.R
import com.saba21.demo.movies.presentation.movieDetails.MovieDetailsFragment
import com.saba21.demo.movies.presentation.movieDetails.util.MOVIE_DETAILS_PARAMS_KEY
import com.saba21.demo.movies.presentation.movieDetails.util.MovieDetailsParams
import com.saba21.demo.movies.presentation.movieList.MovieListFragment

class MainNavigationHandler(private val fragmentManager: FragmentManager) {

    fun onBackPressed() {
        fragmentManager.popBackStack()
    }

    fun goToMovieDetails(movieModel: MovieModel) {
        fragmentManager.commit(true) {
            val fragment = MovieDetailsFragment()
                .apply {
                    arguments =
                        bundleOf(MOVIE_DETAILS_PARAMS_KEY to MovieDetailsParams(movieModel))
                }
            add(R.id.vFragmentContainer, fragment)
            addToBackStack(null)
        }
    }

    fun goToMovieList() {
        fragmentManager.commit(true) {
            replace(R.id.vFragmentContainer, MovieListFragment())
        }
    }

}