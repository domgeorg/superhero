package com.georgiopoulos.core.data.repository.details

import com.georgiopoulos.core.data.repository.home.SuperHeroDataSource
import com.georgiopoulos.core.database.SuperHeroDao
import com.georgiopoulos.core.database.mapper.SuperHeroEntityMapper
import com.georgiopoulos.core.model.superhero.SuperHeroModel
import com.georgiopoulos.core.network.AppDispatchers
import com.georgiopoulos.core.network.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class DetailsRepositoryImpl @Inject constructor(
    private val superheroDao: SuperHeroDao,
    private val supperHeroEntityMapper: SuperHeroEntityMapper,
    private val superHeroDataSource: SuperHeroDataSource,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : DetailsRepository {

    override fun isInSquad(name: String): Flow<Boolean> =
        flow {
            emit(superheroDao.isSuperHeroInDatabase(name))
        }.flowOn(ioDispatcher)

    override suspend fun addInSquad(superHero: SuperHeroModel) {
        withContext(ioDispatcher) {
            superheroDao.insertSuperHero(supperHeroEntityMapper.mapEntity(superHero))
        }
    }

    override suspend fun removeFromSquad(superHero: SuperHeroModel) {
        withContext(ioDispatcher) {
            superheroDao.removeSuperHeron(supperHeroEntityMapper.mapEntity(superHero))
        }
    }

    override fun getSuperHero(): SuperHeroModel? =
        superHeroDataSource.retrieve()

    override fun clearSuperHeroData() {
        superHeroDataSource.remove()
    }
}