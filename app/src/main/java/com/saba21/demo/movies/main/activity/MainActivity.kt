package com.saba21.demo.movies.main.activity

import android.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import com.saba21.demo.domain.models.MovieModel
import com.saba21.demo.movies.R
import com.saba21.demo.movies.base.activity.BaseActivity
import com.saba21.demo.movies.presentation.movieDetails.MovieDetailsFragment
import com.saba21.demo.movies.presentation.movieDetails.util.MOVIE_DETAILS_PARAMS_KEY
import com.saba21.demo.movies.presentation.movieDetails.util.MovieDetailsParams

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val loader: AlertDialog by lazy {
        AlertDialog.Builder(this).setMessage("loading").create()
    }

    override fun showAlert(messageRes: Int) {
        AlertDialog.Builder(this).setMessage(messageRes).show()
    }

    override fun showAlertForResult(messageRes: Int, positiveClick: () -> Unit) {
        AlertDialog.Builder(this).setMessage(messageRes).setPositiveButton(
            android.R.string.ok
        ) { _, _ ->
            positiveClick()
        }.show()
    }

    override fun showLoader() {
        loader.show()
    }

    override fun hideLoader() {
        loader.dismiss()
    }

    override fun goToMovieDetails(movieModel: MovieModel) {
        supportFragmentManager.commit(true) {
            val fragment = MovieDetailsFragment()
                .apply {
                    arguments =
                        bundleOf(MOVIE_DETAILS_PARAMS_KEY to MovieDetailsParams(movieModel))
                }
            replace(R.id.vFragmentContainer, fragment)
            addToBackStack(null)
        }
    }

    override fun popBackStack() {
        onBackPressed()
    }

}