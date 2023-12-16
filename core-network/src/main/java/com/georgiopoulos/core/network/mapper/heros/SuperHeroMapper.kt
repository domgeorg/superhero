package com.georgiopoulos.core.network.mapper.heros

import com.georgiopoulos.core.model.superhero.SuperHeroModel
import com.georgiopoulos.core.network.response.SuperHeroNetworkResponse

/**
 * Interface defining the contract for mapping SuperHeroNetwork data between network responses
 * and domain models.
 *
 * Implementations of this interface are responsible for transforming instances of
 * {@link SuperHeroNetworkResponse} into a list of {@link SuperHeroModel}.
 * This mapping typically involves extracting relevant information and converting it
 * to a format suitable for the application's domain.
 *
 * @see SuperHeroModel
 * @see SuperHeroNetworkResponse
 */
interface SuperHeroMapper {

    /**
     * Maps a {@link SuperHeroNetworkResponse} to a list of {@link SuperHeroModel}.
     *
     * @param response The network response containing SuperHeroNetwork data.
     * @return A list of mapped SuperHeroNetwork models.
     */
    fun mapSuperHero(
        response: SuperHeroNetworkResponse,
    ): List<SuperHeroModel>
}
