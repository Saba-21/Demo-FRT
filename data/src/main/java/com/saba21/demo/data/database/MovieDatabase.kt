package com.saba21.demo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.saba21.demo.data.database.daos.MovieDao
import com.saba21.demo.data.database.entities.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun getMovieDao(): MovieDao

}