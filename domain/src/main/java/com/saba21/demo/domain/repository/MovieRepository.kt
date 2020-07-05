package com.saba21.demo.domain.repository

import com.saba21.demo.domain.models.MovieModel
import io.reactivex.Observable

interface MovieRepository {

    fun getTopRatedMovies(page: Int): Observable<List<MovieModel>>

    fun getPopularMovies(page: Int): Observable<List<MovieModel>>

}