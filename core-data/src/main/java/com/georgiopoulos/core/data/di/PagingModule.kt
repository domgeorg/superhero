package com.georgiopoulos.core.data.di

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.georgiopoulos.core.data.DataConstants
import com.georgiopoulos.core.data.repository.home.DatabasePager
import com.georgiopoulos.core.data.repository.home.NetworkPager
import com.georgiopoulos.core.data.repository.home.SuperHeroPagingDatabaseDataSource
import com.georgiopoulos.core.data.repository.home.SuperHeroPagingNetworkDataSource
import com.georgiopoulos.core.database.SuperHeroDao
import com.georgiopoulos.core.database.mapper.SuperHeroEntityMapper
import com.georgiopoulos.core.model.superhero.SuperHeroModel
import com.georgiopoulos.core.network.mapper.error.ErrorMapper
import com.georgiopoulos.core.network.mapper.heros.SuperHeroMapper
import com.georgiopoulos.core.network.service.Service
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object PagingModule {

    @Provides
    @Singleton
    @NetworkPager
    fun provideSuperHeroNetworkPager(
        service: Service,
        superHeroMapper: SuperHeroMapper,
        errorMapper: ErrorMapper,
    ): Pager<Int, SuperHeroModel> {
        return Pager(
            config = PagingConfig(
                pageSize = DataConstants.PAGING_SIZE,
                initialLoadSize = DataConstants.INITIAL_LOAD_SIZE,
                prefetchDistance = DataConstants.PREFETCH_DISTANCE,
            ),
            pagingSourceFactory = {
                SuperHeroPagingNetworkDataSource(
                    service = service,
                    superHeroMapper = superHeroMapper,
                    errorMapper = errorMapper,
                )
            }
        )
    }

    @Provides
    @Singleton
    @DatabasePager
    fun provideSuperHeroDatabasePager(
        superHeroDao: SuperHeroDao,
        superHeroEntityMapper: SuperHeroEntityMapper,
    ): Pager<Int, SuperHeroModel> {
        return Pager(
            config = PagingConfig(
                pageSize = DataConstants.PAGING_SIZE,
                initialLoadSize = DataConstants.INITIAL_LOAD_SIZE,
                prefetchDistance = DataConstants.PREFETCH_DISTANCE,
            ),
            pagingSourceFactory = {
                SuperHeroPagingDatabaseDataSource(
                    superHeroDao = superHeroDao,
                    superHeroEntityMapper = superHeroEntityMapper,
                )
            }
        )
    }
}