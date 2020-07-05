package com.saba21.demo.data.repository

import com.saba21.demo.data.api.MovieService
import com.saba21.demo.data.converters.MovieDomainConverter
import com.saba21.demo.data.database.MovieDatabase
import com.saba21.demo.data.util.scedulers.async
import com.saba21.demo.domain.models.MovieModel
import com.saba21.demo.domain.repository.MovieRepository
import io.reactivex.Observable
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService,
    private val movieDatabase: MovieDatabase,
    private val movieDomainConverter: MovieDomainConverter
) : MovieRepository {

    override fun getTopRatedMovies(page: Int): Observable<List<MovieModel>> {
        return movieService.getTopRatedMovies(page)
            .map {
                it.results.map { item ->
                    movieDomainConverter.fromDto(item)
                }
            }.async()
    }

    override fun getPopularMovies(page: Int): Observable<List<MovieModel>> {
        return movieService.getPopularMovies(page)
            .map {
                it.results.map { item ->
                    movieDomainConverter.fromDto(item)
                }
            }.async()
    }

}