package com.georgiopoulos.core.database.mapper

import com.georgiopoulos.core.database.entity.SuperHeroEntity
import com.georgiopoulos.core.model.superhero.SuperHeroModel

/**
 * Interface for mapping between the domain model {@link SuperHeroModel} and
 * the database entity {@link SuperHeroEntity}. Implementations of this interface
 * define the mapping logic for converting instances between the model and entity representations.
 */
interface SuperHeroEntityMapper {

    /**
     * Maps a {@link SuperHeroModel} instance to a {@link SuperHeroEntity} instance.
     *
     * @param superHero The superhero model to be mapped.
     * @return The corresponding superhero entity.
     */
    fun mapEntity(
        superHero: SuperHeroModel,
    ): SuperHeroEntity

    /**
     * Maps a {@link SuperHeroEntity} instance to a {@link SuperHeroModel} instance.
     *
     * @param entity The superhero entity to be mapped.
     * @return The corresponding superhero model.
     */
    fun mapModel(
        entity: SuperHeroEntity,
    ): SuperHeroModel
}