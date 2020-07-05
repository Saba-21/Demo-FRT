package com.saba21.demo.data.converters

import com.saba21.demo.data.api.dto.MovieDTO
import com.saba21.demo.data.converters.base.BaseDomainConverter
import com.saba21.demo.data.database.entities.MovieEntity
import com.saba21.demo.data.di.POSTER_ADDRESS
import com.saba21.demo.domain.models.MovieModel
import javax.inject.Inject
import javax.inject.Named

class MovieDomainConverter
@Inject constructor(
    @Named(POSTER_ADDRESS) val posterAddress: String
) : BaseDomainConverter<MovieModel, MovieEntity, MovieDTO> {

    override fun fromDto(item: MovieDTO): MovieModel {
        return MovieModel(
            id = item.id,
            title = item.title,
            originalTitle = item.originalTitle,
            overview = item.overview,
            releaseDate = item.releaseDate,
            posterUrl = posterAddress + item.posterUrl,
            rating = item.rating
        )
    }

    override fun fromEntity(item: MovieEntity): MovieModel {
        return MovieModel(
            id = item.id,
            title = item.title,
            originalTitle = item.originalTitle,
            overview = item.overview,
            releaseDate = item.releaseDate,
            posterUrl = item.posterUrl,
            rating = item.rating
        )
    }

    override fun toEntity(item: MovieModel): MovieEntity {
        return MovieEntity(
            id = item.id,
            title = item.title,
            originalTitle = item.originalTitle,
            overview = item.overview,
            releaseDate = item.releaseDate,
            posterUrl = item.posterUrl,
            rating = item.rating
        )
    }

}