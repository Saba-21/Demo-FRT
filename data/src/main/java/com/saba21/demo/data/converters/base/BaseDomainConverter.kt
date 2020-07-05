package com.saba21.demo.data.converters.base

interface BaseDomainConverter<Domain, Entity, DTO> {

    fun fromDto(item: DTO): Domain

    fun fromEntity(item: Entity): Domain

    fun toEntity(item: Domain): Entity

}