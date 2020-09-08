package com.saba21.demo.movies.main.activity.di

import com.saba21.demo.movies.presentation.movieDetails.di.MovieDetailsComponent
import com.saba21.demo.movies.presentation.movieList.di.MovieListComponent
import com.saba21.demo.movies.presentation.splash.di.SplashComponent

interface FragmentSubComponentProvider {

    fun getSplashComponentFactory(): SplashComponent.Factory

    fun getMovieDetailsComponentFactory(): MovieDetailsComponent.Factory

    fun getMovieListComponentFactory(): MovieListComponent.Factory

}