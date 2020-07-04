package com.saba21.demo.movies.main.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.saba21.demo.movies.R
import com.saba21.demo.movies.main.activity.di.ActivityComponent
import com.saba21.demo.movies.main.application.App
import com.saba21.demo.movies.presentation.movieDetails.MovieDetailsFragment
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
            viewModel.mainViewState
                .subscribe {
                    when (it) {
                        is MainViewState.Navigation -> handleNavigation(it)
                    }
                }
        )
    }

    private fun handleNavigation(navigation: MainViewState.Navigation) {
        when (navigation) {
            is MainViewState.Navigation.GoToMovieList -> {
                supportFragmentManager.commit(true) {
                    replace(R.id.vFragmentContainer, MovieListFragment())
                }
            }
            is MainViewState.Navigation.GoToMovieDetails -> {
                supportFragmentManager.commit(true) {
                    replace(R.id.vFragmentContainer, MovieDetailsFragment())
                    addToBackStack(null)
                }
            }
        }
    }

}