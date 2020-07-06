package com.saba21.demo.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.saba21.demo.data.database.entities.MovieEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(movieEntity: MovieEntity): Completable

    @Query("SELECT * from movie")
    fun getMovies(): Observable<List<MovieEntity>>

    @Query("SELECT COUNT(*) from movie where id like :id")
    fun getMovieCountById(id: Int): Observable<Int>
}