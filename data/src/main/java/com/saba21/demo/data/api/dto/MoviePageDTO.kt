package com.saba21.demo.data.api.dto

import kotlinx.serialization.Serializable

@Serializable
class MoviePageDTO(
    val page:Int,
    val results :List<MovieDTO>
)