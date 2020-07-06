package com.saba21.demo.movies.presentation.movieList

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.saba21.demo.domain.models.MovieModel
import com.saba21.demo.movies.R
import com.saba21.demo.movies.base.fragment.BaseFragment
import com.saba21.demo.movies.main.activity.di.ActivityComponent
import com.saba21.demo.movies.presentation.movieList.di.MovieListComponent
import com.saba21.demo.movies.presentation.movieList.util.MovieListAdapter
import com.saba21.demo.movies.presentation.movieList.util.MoviePages
import com.saba21.demo.movies.presentation.movieList.util.MoviePagesAdapter
import com.saba21.simplepagingadapter.library.PagingManager
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListFragment : BaseFragment<MovieListActions, MovieListViewState, MovieListViewModel>(
    R.layout.fragment_movie_list,
    MovieListViewModel::class
) {

    private lateinit var popularPageManager: PagingManager<MovieModel>
    private lateinit var topRatedPageManager: PagingManager<MovieModel>
    private lateinit var favoritesAdapter: MovieListAdapter

    override fun getComponent(activityComponent: ActivityComponent): MovieListComponent {
        return activityComponent.getMovieListComponentFactory().create(this)
    }

    override fun onDraw(view: View, lastState: MovieListViewState?) {
        super.onDraw(view, lastState)
        initMoviePageManagers()
        setUpMoviePages()
    }

    override fun reflectState(state: MovieListViewState) {
        when (state) {
            is MovieListViewState.DrawTopRatedMovies -> {
                topRatedPageManager.setData(state.pageIndex, state.movies)
            }
            is MovieListViewState.DrawPopularMovies -> {
                popularPageManager.setData(state.pageIndex, state.movies)
            }
            is MovieListViewState.DrawFavoriteMovies -> {
                favoritesAdapter.bindData(state.movies)
            }
        }
    }

    private fun setUpMoviePages() {
        val pageTitles =
            MoviePages.values().toList().sortedBy { it.position }.map { getString(it.titleRes) }
        vpMovies.adapter = MoviePagesAdapter(pageTitles, requestAdapter = ::requestMoviePageAdapter)
        vpMovies.offscreenPageLimit = pageTitles.size
        tbMovies.setupWithViewPager(vpMovies)
    }

    private fun initMoviePageManagers() {
        popularPageManager = getMoviePageManager { pageIndex ->
            postAction(MovieListActions.LoadPopularMoviesPage(pageIndex))
        }
        topRatedPageManager = getMoviePageManager { pageIndex ->
            postAction(MovieListActions.LoadTopRatedMoviesPage(pageIndex))
        }
        favoritesAdapter = MovieListAdapter()
        favoritesAdapter.setOnClick {
            postAction(MovieListActions.Navigation.GoToDetails(it))
        }
        postAction(MovieListActions.LoadFavoriteMoviesPage)
    }

    private fun requestMoviePageAdapter(position: Int): RecyclerView.Adapter<*> {
        return when (MoviePages.values().associateBy(MoviePages::position)[position]!!) {
            MoviePages.Popular -> popularPageManager.getAdapter()
            MoviePages.TopRated -> topRatedPageManager.getAdapter()
            MoviePages.Favourite -> favoritesAdapter
        }
    }

    private fun getMoviePageManager(
        onDataRequested: (Int) -> Unit
    ): PagingManager<MovieModel> {
        return PagingManager
            .builder<MovieModel>()
            .setLifecycle(this)
            .setPageSize(viewModel.moviePageSize)
            .setLayout(R.layout.item_movie)
            .onItemBind { itemView, _, item ->
                onBindMovieItem(itemView, item)
            }.checkItemIds { oldItem, newItem ->
                oldItem.id == newItem.id
            }.checkItemContent { oldItem, newItem ->
                oldItem == newItem
            }.onDataRequested { pageIndex, _ ->
                onDataRequested.invoke(pageIndex)
            }.build()
    }

    private fun onBindMovieItem(itemView: View, item: MovieModel) {
        itemView.onClick {
            MovieListActions.Navigation.GoToDetails(item)
        }
        Glide.with(itemView.context)
            .load(item.posterUrl)
            .error(R.drawable.shape_image_placeholder)
            .placeholder(R.drawable.shape_image_placeholder)
            .transform(CenterCrop(), RoundedCorners(24))
            .into(itemView.ivPoster)
    }

}