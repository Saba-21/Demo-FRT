package com.saba21.demo.domain.useCase

import com.saba21.demo.domain.base.BaseUseCase
import com.saba21.demo.domain.models.MovieModel
import com.saba21.demo.domain.repository.MovieRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetFavoriteMoviesUseCase
@Inject constructor(repository: MovieRepository) :
    BaseUseCase<Unit, List<MovieModel>>(repository) {

    override fun create(arg: Unit): Observable<List<MovieModel>> {
        return repository.getFavoriteMovies()
    }

}