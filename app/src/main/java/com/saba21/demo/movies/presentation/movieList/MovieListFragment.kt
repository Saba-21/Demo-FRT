package com.saba21.demo.movies.presentation.movieList

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.saba21.demo.domain.models.MovieModel
import com.saba21.demo.movies.R
import com.saba21.demo.movies.base.fragment.BaseFragment
import com.saba21.demo.movies.custom.list.MovieListAdapter
import com.saba21.demo.movies.custom.list.MovieListItemDecoration
import com.saba21.demo.movies.main.activity.di.ActivityComponent
import com.saba21.demo.movies.presentation.movieList.di.MovieListComponent
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieListFragment :
    BaseFragment<MovieListActions, MovieListViewState, MovieListViewModel>() {

    override val layoutRes = R.layout.fragment_movie_list
    override val viewModelClass = MovieListViewModel::class

    private lateinit var favoritesAdapter: MovieListAdapter

    override fun getComponent(activityComponent: ActivityComponent): MovieListComponent {
        return activityComponent.getMovieListComponentFactory().create(this)
    }

    override fun onDraw(view: View, lastState: MovieListViewState?) {
        super.onDraw(view, lastState)
        initMovieList()
        redrawState(lastState?.currentState)
    }

    override fun reflectState(state: MovieListViewState) {
        when (state) {
            is MovieListViewState.DrawTopRatedMovies -> {
                drawMovieList(state.movies)
            }
        }
    }

    private fun redrawState(currentState: MovieListViewState.MovieListViewData?) {
        if (currentState != null)
            drawMovieList(currentState.topRatedMovies ?: emptyList())
    }

    private fun drawMovieList(movies: List<MovieModel>) {
        favoritesAdapter.bindData(movies)
    }

    private fun initMovieList() {
        favoritesAdapter = MovieListAdapter()
        favoritesAdapter.setOnClick {
            postAction(MovieListActions.Navigation.GoToDetails(it))
        }
        rvMovies.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = favoritesAdapter
            if (itemDecorationCount == 0)
                addItemDecoration(MovieListItemDecoration(context))
            adapter = favoritesAdapter
        }
    }
}