package com.georgiopoulos.core.network.mapper.heros

import com.georgiopoulos.core.model.superhero.SuperHeroModel
import com.georgiopoulos.core.network.response.SuperHeroNetwork
import com.georgiopoulos.core.network.response.SuperHeroNetworkResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SuperHeroMapperImpl @Inject constructor() : SuperHeroMapper {
    override fun mapSuperHero(response: SuperHeroNetworkResponse): List<SuperHeroModel> =
        response.data.results.map(::mapToSuperHeroModel)


    private fun mapToSuperHeroModel(superHeroNetwork: SuperHeroNetwork): SuperHeroModel {
        val thumbnailUrl = superHeroNetwork.thumbnail.run {
            "${path.replace(HTTP, HTTPS)}$THUMBNAIL_PATH_STANDARD$extension"
        }

        return SuperHeroModel(
            id = superHeroNetwork.id,
            name = superHeroNetwork.name,
            standardImageUrl = thumbnailUrl,
            description = superHeroNetwork.description,
        )
    }

    companion object {
        const val HTTP = "http"
        const val HTTPS = "https"
        const val THUMBNAIL_PATH_STANDARD = "/standard_fantastic."
    }
}
