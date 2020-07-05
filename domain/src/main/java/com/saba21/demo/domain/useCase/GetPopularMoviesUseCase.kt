package com.saba21.demo.domain.useCase

import com.saba21.demo.domain.base.BaseUseCase
import com.saba21.demo.domain.models.MovieModel
import com.saba21.demo.domain.repository.MovieRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetPopularMoviesUseCase
@Inject constructor(repository: MovieRepository) :
    BaseUseCase<Int, List<MovieModel>>(repository) {

    override fun create(arg: Int): Observable<List<MovieModel>> {
        return repository.getPopularMovies(arg)
    }

}