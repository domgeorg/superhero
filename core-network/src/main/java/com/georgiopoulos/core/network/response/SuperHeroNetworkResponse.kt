package com.georgiopoulos.core.network.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SuperHeroNetworkResponse(
    val data: NetworkResults,
)

@JsonClass(generateAdapter = true)
data class NetworkResults(
    val results: List<SuperHeroNetwork>,
)

@JsonClass(generateAdapter = true)
data class SuperHeroNetwork(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: HeroThumbnail,
)

@JsonClass(generateAdapter = true)
data class HeroThumbnail(
    val path: String,
    val extension: String,
)