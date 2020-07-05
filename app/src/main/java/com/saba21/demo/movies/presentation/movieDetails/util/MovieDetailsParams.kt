package com.saba21.demo.movies.presentation.movieDetails.util

import com.saba21.demo.domain.models.MovieModel
import java.io.Serializable

const val MOVIE_DETAILS_PARAMS_KEY = "MOVIE_DETAILS_PARAMS_KEY"

class MovieDetailsParams(val movieModel: MovieModel) : Serializable