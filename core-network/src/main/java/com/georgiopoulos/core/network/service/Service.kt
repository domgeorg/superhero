package com.georgiopoulos.core.network.service

import com.georgiopoulos.core.network.response.SuperHeroNetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("/v1/public/characters")
    suspend fun fetchSuperHeroList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
    ): SuperHeroNetworkResponse
}
