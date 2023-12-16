package com.georgiopoulos.core.data.repository.details

import androidx.annotation.WorkerThread
import com.georgiopoulos.core.model.superhero.SuperHeroModel
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {

    @WorkerThread
    fun isInSquad(
        name: String,
    ): Flow<Boolean>

    @WorkerThread
    suspend fun addInSquad(
        superHero: SuperHeroModel,
    )

    @WorkerThread
    suspend fun removeFromSquad(
        superHero: SuperHeroModel,
    )

    fun getSuperHero(): SuperHeroModel?

    fun clearSuperHeroData()
}