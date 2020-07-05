package com.saba21.demo.data.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MovieDTO(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("original_title")
    val originalTitle: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("poster_path")
    val posterUrl: String?,
    @SerialName("vote_average")
    val rating: Float
)