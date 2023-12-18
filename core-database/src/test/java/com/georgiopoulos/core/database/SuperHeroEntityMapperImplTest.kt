package com.georgiopoulos.core.database

import com.georgiopoulos.core.database.entity.SuperHeroEntity
import com.georgiopoulos.core.database.mapper.SuperHeroEntityMapperImpl
import com.georgiopoulos.core.model.superhero.SuperHeroModel
import org.junit.Assert.assertEquals
import org.junit.Test

class SuperHeroEntityMapperImplTest {

    @Test
    fun `Given SuperHeroModel, When mapEntity is called, Then return SuperHeroEntity`() {
        // Given
        val mapper = SuperHeroEntityMapperImpl()
        val superHeroModel = SuperHeroModel(
            id = 1,
            name = "Spider-Man",
            standardImageUrl = "https://example.com/spiderman.jpg",
            description = "Your friendly neighborhood Spider-Man"
        )

        // When
        val result = mapper.mapEntity(superHeroModel)

        // Then
        assertEquals(
            /* expected = */
            SuperHeroEntity(
                id = 1,
                name = "Spider-Man",
                standardImageUrl = "https://example.com/spiderman.jpg",
                description = "Your friendly neighborhood Spider-Man"
            ),
            /* actual = */ result,
        )
    }

    @Test
    fun `Given SuperHeroEntity, When mapModel is called, Then return SuperHeroModel`() {
        // Given
        val mapper = SuperHeroEntityMapperImpl()
        val superHeroEntity = SuperHeroEntity(
            id = 1,
            name = "Iron Man",
            standardImageUrl = "https://example.com/ironman.jpg",
            description = "Genius, billionaire, playboy, philanthropist"
        )

        // When
        val result = mapper.mapModel(superHeroEntity)

        // Then
        assertEquals(
            /* expected = */
            SuperHeroModel(
                id = 1,
                name = "Iron Man",
                standardImageUrl = "https://example.com/ironman.jpg",
                description = "Genius, billionaire, playboy, philanthropist"
            ),
            /* actual = */ result,
        )
    }

}