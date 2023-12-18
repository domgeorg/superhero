package com.georgiopoulos.core.network.mapper.heros

import com.georgiopoulos.core.model.superhero.SuperHeroModel
import com.georgiopoulos.core.network.response.HeroThumbnail
import com.georgiopoulos.core.network.response.NetworkResults
import com.georgiopoulos.core.network.response.SuperHeroNetwork
import com.georgiopoulos.core.network.response.SuperHeroNetworkResponse
import org.junit.Assert.assertEquals
import org.junit.Test


class SuperHeroMapperImplTest {

    @Test
    fun `Given SuperHeroNetworkResponse with heroes, When mapSuperHero is called, Then return list of SuperHeroModel`() {
        // Given
        val mapper = SuperHeroMapperImpl()
        val networkResponse = SuperHeroNetworkResponse(
            data = NetworkResults(
                results = listOf(
                    SuperHeroNetwork(
                        id = 1,
                        name = "Spider-Man",
                        description = "Your friendly neighborhood Spider-Man",
                        thumbnail = HeroThumbnail(
                            path = "http://example.com/spiderman",
                            extension = "jpg",
                        )
                    ),
                )
            )
        )

        // When
        val result = mapper.mapSuperHero(networkResponse)

        // Then
        assertEquals(result.size, 1)
        assertEquals(
            /* expected = */
            SuperHeroModel(
                id = 1,
                name = "Spider-Man",
                standardImageUrl = "https://example.com/spiderman/standard_fantastic.jpg",
                description = "Your friendly neighborhood Spider-Man",
            ),
            /* actual = */ result[0],
        )
    }
}