package com.saba21.demo.data.repository

import com.saba21.demo.data.api.MovieService
import com.saba21.demo.data.converters.MovieDomainConverter
import com.saba21.demo.data.database.MovieDatabase
import com.saba21.demo.data.util.scedulers.async
import com.saba21.demo.domain.models.MovieModel
import com.saba21.demo.domain.repository.MovieRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
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

    override fun saveFavoriteMovie(movieModel: MovieModel): Observable<Unit> {
        return movieDatabase.getMovieDao().saveMovie(movieDomainConverter.toEntity(movieModel))
            .andThen(Observable.just(Unit)).async()
    }

    override fun getFavoriteMovies(): Observable<List<MovieModel>> {
        return movieDatabase.getMovieDao()
            .getMovies()
            .map {
                it.map { item ->
                    movieDomainConverter.fromEntity(item)
                }
            }.async()
    }

    override fun getFavoriteMovieCountById(id: Int): Observable<Int> {
        return movieDatabase.getMovieDao()
            .getMovieCountById(id)
            .async()
    }

}