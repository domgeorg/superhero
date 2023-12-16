package com.georgiopoulos.core.data.repository.home

import androidx.annotation.WorkerThread
import androidx.paging.PagingData
import com.georgiopoulos.core.model.superhero.SuperHeroModel
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    @WorkerThread
    fun fetchSuperHeroListFromNetwork(): Flow<PagingData<SuperHeroModel>>

    @WorkerThread
    fun fetchSuperHeroListFromStorage(): Flow<PagingData<SuperHeroModel>>

    @WorkerThread
    fun save(superHero: SuperHeroModel)
}