package com.georgiopoulos.core.data.repository.home

import androidx.paging.Pager
import androidx.paging.PagingData
import com.georgiopoulos.core.model.superhero.SuperHeroModel
import com.georgiopoulos.core.network.AppDispatchers
import com.georgiopoulos.core.network.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class HomeRepositoryImpl @Inject constructor(
    @NetworkPager private val superHeroNetworkPager: Pager<Int, SuperHeroModel>,
    @DatabasePager private val superHeroDatabasePager: Pager<Int, SuperHeroModel>,
    private val superHeroDataSource: SuperHeroDataSource,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : HomeRepository {

    override fun fetchSuperHeroListFromNetwork(): Flow<PagingData<SuperHeroModel>> =
        superHeroNetworkPager.flow.flowOn(ioDispatcher)

    override fun fetchSuperHeroListFromStorage(): Flow<PagingData<SuperHeroModel>> =
        superHeroDatabasePager.flow.flowOn(ioDispatcher)

    override fun save(superHero: SuperHeroModel) {
        superHeroDataSource.save(superHero)
    }
}
