package com.saba21.demo.movies.presentation.movieDetails.di

import com.saba21.demo.movies.base.di.scopes.FragmentScope
import com.saba21.demo.movies.presentation.movieDetails.MovieDetailsFragment
import com.saba21.demo.movies.presentation.movieDetails.util.MOVIE_DETAILS_PARAMS_KEY
import com.saba21.demo.movies.presentation.movieDetails.util.MovieDetailsParams
import dagger.Module
import dagger.Provides

@Module
class MovieDetailsModule {

    @Provides
    @FragmentScope
    fun provideArguments(fragment: MovieDetailsFragment): MovieDetailsParams {
        return fragment.requireArguments()
            .getSerializable(MOVIE_DETAILS_PARAMS_KEY) as MovieDetailsParams
    }

}