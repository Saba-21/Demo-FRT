package com.saba21.demo.domain.models

import java.io.Serializable

class MovieModel(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val releaseDate: String,
    val posterUrl: String,
    val rating: Float
) : Serializable