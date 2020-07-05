package com.saba21.demo.movies.presentation.movieList

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.saba21.demo.domain.models.MovieModel
import com.saba21.demo.movies.R
import com.saba21.demo.movies.base.fragment.BaseFragment
import com.saba21.demo.movies.main.activity.di.ActivityComponent
import com.saba21.demo.movies.presentation.movieList.di.MovieListComponent
import com.saba21.simplepagingadapter.library.PagingManager
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListFragment : BaseFragment<MovieListActions, MovieListViewState, MovieListViewModel>(
    R.layout.fragment_movie_list,
    MovieListViewModel::class
) {

    private lateinit var pagingManager: PagingManager<MovieModel>

    override fun getComponent(activityComponent: ActivityComponent): MovieListComponent {
        return activityComponent.getMovieListComponentFactory().create(this)
    }

    override fun onDraw(view: View, lastState: MovieListViewState?) {
        super.onDraw(view, lastState)

        pagingManager = PagingManager
            .builder<MovieModel>()
            .setLifecycle(this)
            .setPageSize(20)
            .setLayout(R.layout.item_movie)
            .onItemBind { itemView, _, item ->
                onBindItem(itemView, item)
            }.checkItemIds { oldItem, newItem ->
                oldItem.id == newItem.id
            }.checkItemContent { oldItem, newItem ->
                oldItem == newItem
            }.onDataRequested { pageIndex, _ ->
                postAction(MovieListActions.LoadTopRatedMoviesPage(pageIndex))
            }.build()

        rvMovies.adapter = pagingManager.getAdapter()
        rvMovies.layoutManager = LinearLayoutManager(context)

    }

    private fun onBindItem(itemView: View, item: MovieModel) {
        itemView.tvTitle.text = item.title
    }

    override fun reflectState(state: MovieListViewState) {
        when (state) {
            is MovieListViewState.DrawTopRatedMovies -> {
                pagingManager.setData(state.pageIndex, state.movies)
            }
        }
    }

}