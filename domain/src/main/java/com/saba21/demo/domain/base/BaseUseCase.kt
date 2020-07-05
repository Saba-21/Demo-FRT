package com.saba21.demo.domain.base

import com.saba21.demo.domain.repository.MovieRepository
import io.reactivex.Observable

abstract class BaseUseCase<in A, B>(protected val repository: MovieRepository) {

    abstract fun create(arg: A): Observable<B>

}