package com.georgiopoulos.core.database.mapper

import com.georgiopoulos.core.database.entity.SuperHeroEntity
import com.georgiopoulos.core.model.superhero.SuperHeroModel
import javax.inject.Inject

internal class SuperHeroEntityMapperImpl @Inject constructor() : SuperHeroEntityMapper {
    override fun mapEntity(superHero: SuperHeroModel): SuperHeroEntity {
        return SuperHeroEntity(
            id = superHero.id,
            name = superHero.name,
            standardImageUrl = superHero.standardImageUrl,
            description = superHero.description,
        )
    }

    override fun mapModel(entity: SuperHeroEntity): SuperHeroModel {
        return SuperHeroModel(
            id = entity.id,
            name = entity.name,
            standardImageUrl = entity.standardImageUrl,
            description = entity.description,
        )
    }
}
