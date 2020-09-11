package com.saba21.demo.movies.main.activity.di

import com.saba21.demo.movies.presentation.movieDetails.di.MovieDetailsComponent
import com.saba21.demo.movies.presentation.movieList.di.MovieListComponent

interface FragmentSubComponentProvider {

    fun getMovieDetailsComponentFactory(): MovieDetailsComponent.Factory

    fun getMovieListComponentFactory(): MovieListComponent.Factory

}