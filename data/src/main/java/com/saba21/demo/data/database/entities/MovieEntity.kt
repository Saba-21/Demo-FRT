package com.saba21.demo.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
class MovieEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val releaseDate: String,
    val posterUrl: String,
    val rating: Float
)