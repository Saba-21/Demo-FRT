package com.saba21.demo.data.api

import com.saba21.demo.data.api.dto.MoviePageDTO
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("top_rated")
    fun getTopRatedMovies(@Query("page") page: Int): Observable<MoviePageDTO>

    @GET("popular")
    fun getPopularMovies(@Query("page") page: Int): Observable<MoviePageDTO>

}