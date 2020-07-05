package com.saba21.demo.domain.repository

import io.reactivex.Observable

interface MovieRepository {

    fun getTopRatedMovies(page: Int): Observable<Unit>

    fun getPopularMovies(page: Int): Observable<Unit>

}