package com.saba21.demo.domain.useCase

import com.saba21.demo.domain.base.BaseUseCase
import com.saba21.demo.domain.models.MovieModel
import com.saba21.demo.domain.repository.MovieRepository
import io.reactivex.Observable
import javax.inject.Inject

class SaveFavoriteMoviesUseCase
@Inject constructor(repository: MovieRepository) :
    BaseUseCase<MovieModel, Unit>(repository) {

    override fun create(arg: MovieModel): Observable<Unit> {
        return repository.saveFavoriteMovie(arg)
    }

}