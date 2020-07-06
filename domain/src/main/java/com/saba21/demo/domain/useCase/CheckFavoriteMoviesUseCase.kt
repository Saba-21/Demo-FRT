package com.saba21.demo.domain.useCase

import com.saba21.demo.domain.base.BaseUseCase
import com.saba21.demo.domain.repository.MovieRepository
import io.reactivex.Observable
import javax.inject.Inject

class CheckFavoriteMoviesUseCase
@Inject constructor(repository: MovieRepository) :
    BaseUseCase<Int, Boolean>(repository) {

    override fun create(arg: Int): Observable<Boolean> {
        return repository.getFavoriteMovieCountById(arg)
            .map {
                it != 0
            }
    }

}