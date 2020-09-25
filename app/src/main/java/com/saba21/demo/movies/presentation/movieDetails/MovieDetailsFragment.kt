package com.saba21.demo.movies.presentation.movieDetails

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.saba21.demo.domain.models.MovieModel
import com.saba21.demo.movies.R
import com.saba21.demo.movies.base.fragment.BaseFragment
import com.saba21.demo.movies.main.activity.di.ActivityComponent
import com.saba21.demo.movies.presentation.movieDetails.di.MovieDetailsComponent
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.fragment_movie_details.*

class MovieDetailsFragment :
    BaseFragment<MovieDetailsActions, MovieDetailsViewState, MovieDetailsViewModel>() {

    override val layoutRes = R.layout.fragment_movie_details

    override val viewModelClass = MovieDetailsViewModel::class

    override fun getComponent(activityComponent: ActivityComponent): MovieDetailsComponent {
        return activityComponent.getMovieDetailsComponentFactory().create(this)
    }

    override fun onDraw(view: View, lastState: MovieDetailsViewState?) {
        bindActions()
    }

    override fun reflectState(state: MovieDetailsViewState) {
        when (state) {
            is MovieDetailsViewState.DrawMovieDetails -> drawMovieDetails(state.movieModel)
            is MovieDetailsViewState.MakeMovieFavorite -> setFavoriteStatus(state.isFavorite)
        }
    }

    private fun setFavoriteStatus(isFavorite: Boolean) {
        ivFavorite.isSelected = isFavorite
    }

    private fun bindActions() {
        ivBack.onClick {
            MovieDetailsActions.Navigation.GoBack
        }
        ivFavorite.onClick {
            MovieDetailsActions.SaveFavorite
        }
    }

    private fun drawMovieDetails(movieModel: MovieModel) {
        drawPosters(movieModel)
        tvMovieTitle.text = movieModel.title
        tvMovieRating.text =
            getString(R.string.movie_details_field_name_rating, movieModel.rating.toString())
        tvMovieOriginalTitle.text =
            getString(R.string.movie_details_field_name_original_title, movieModel.originalTitle)
        tvMovieReleaseDate.text =
            getString(R.string.movie_details_field_name_release_date, movieModel.releaseDate)
        tvMovieOverview.text = movieModel.overview
    }

    private fun drawPosters(movieModel: MovieModel) {
        Glide.with(requireContext())
            .load(movieModel.posterUrl)
            .error(R.drawable.shape_background_placeholder)
            .placeholder(R.drawable.shape_image_placeholder)
            .transform(BlurTransformation(24), CenterCrop())
            .into(ivDetailsPosterBackground)

        Glide.with(requireContext())
            .load(movieModel.posterUrl)
            .error(R.drawable.shape_image_placeholder)
            .placeholder(R.drawable.shape_image_placeholder)
            .transform(CenterCrop(), RoundedCorners(24))
            .into(ivDetailsPoster)
    }

}