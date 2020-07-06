package com.saba21.demo.movies.main.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import com.saba21.demo.movies.R
import com.saba21.demo.movies.main.activity.di.ActivityComponent
import com.saba21.demo.movies.main.application.App
import com.saba21.demo.movies.presentation.movieDetails.MovieDetailsFragment
import com.saba21.demo.movies.presentation.movieDetails.util.MOVIE_DETAILS_PARAMS_KEY
import com.saba21.demo.movies.presentation.movieDetails.util.MovieDetailsParams
import com.saba21.demo.movies.presentation.movieList.MovieListFragment
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val component: ActivityComponent by lazy {
        (application as App).appComponent
            .getActivityComponentFactory()
            .create(this)
    }

    val activityComponent: ActivityComponent get() = component

    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        compositeDisposable.add(
            viewModel.mainViewState.subscribe(this::reflectState)
        )
    }

    private fun reflectState(it: MainViewState?) {
        when (it) {
            is MainViewState.Navigation -> handleNavigation(it)
            is MainViewState.Error -> handleError(it)
        }
    }

    private fun handleError(error: MainViewState.Error) {
        when (error) {
            is MainViewState.Error.CommonError -> showErrorMessage(error.type.messageRes)
        }
    }

    private fun showErrorMessage(messageRes: Int) {
        AlertDialog.Builder(this).setMessage(messageRes).show()
    }

    private fun handleNavigation(navigation: MainViewState.Navigation) {
        when (navigation) {
            is MainViewState.Navigation.GoBack -> onBackPressed()
            is MainViewState.Navigation.GoToMovieList -> goToMovieList()
            is MainViewState.Navigation.GoToMovieDetails -> goToMovieDetails(navigation)
        }
    }

    private fun goToMovieDetails(navigation: MainViewState.Navigation.GoToMovieDetails) {
        supportFragmentManager.commit(true) {
            val fragment = MovieDetailsFragment()
                .apply {
                    arguments =
                        bundleOf(MOVIE_DETAILS_PARAMS_KEY to MovieDetailsParams(navigation.movieModel))
                }
            add(R.id.vFragmentContainer, fragment)
            addToBackStack(null)
        }
    }

    private fun goToMovieList() {
        supportFragmentManager.commit(true) {
            replace(R.id.vFragmentContainer, MovieListFragment())
        }
    }

}