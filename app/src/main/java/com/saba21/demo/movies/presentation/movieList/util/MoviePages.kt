package com.saba21.demo.movies.presentation.movieList.util

import androidx.annotation.StringRes
import com.saba21.demo.movies.R

enum class MoviePages(@StringRes val titleRes: Int, val position: Int) {
    Popular(R.string.tab_title_popular, 0),
    TopRated(R.string.tab_title_top_rated, 1),
    Favourite(R.string.tab_title_favourite, 2)
}