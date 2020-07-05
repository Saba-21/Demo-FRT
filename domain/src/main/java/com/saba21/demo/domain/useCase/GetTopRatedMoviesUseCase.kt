package com.saba21.demo.domain.useCase

import com.saba21.demo.domain.base.BaseUseCase
import com.saba21.demo.domain.repository.MovieRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetTopRatedMoviesUseCase
@Inject constructor(repository: MovieRepository) :
    BaseUseCase<Int, Unit>(repository) {

    override fun create(arg: Int): Observable<Unit> {
        return repository.getTopRatedMovies(arg)
    }

}