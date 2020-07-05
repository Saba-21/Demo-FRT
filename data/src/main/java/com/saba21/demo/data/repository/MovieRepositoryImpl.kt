package com.saba21.demo.data.repository

import com.saba21.demo.data.api.MovieService
import com.saba21.demo.data.database.MovieDatabase
import com.saba21.demo.data.util.scedulers.async
import com.saba21.demo.domain.repository.MovieRepository
import io.reactivex.Observable
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService,
    private val movieDatabase: MovieDatabase
) : MovieRepository {

    override fun getTopRatedMovies(page: Int): Observable<Unit> {
        return movieService.getTopRatedMovies(page).async()
    }

    override fun getPopularMovies(page: Int): Observable<Unit> {
        return movieService.getPopularMovies(page).async()
    }

}