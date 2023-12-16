package com.georgiopoulos.core.data.di

import com.georgiopoulos.core.data.repository.details.DetailsRepository
import com.georgiopoulos.core.data.repository.details.DetailsRepositoryImpl
import com.georgiopoulos.core.data.repository.home.HomeRepository
import com.georgiopoulos.core.data.repository.home.HomeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @Binds
    fun bindsMainRepository(
        mainRepositoryImpl: HomeRepositoryImpl,
    ): HomeRepository

    @Binds
    fun bindsDetailRepository(
        detailRepositoryImpl: DetailsRepositoryImpl,
    ): DetailsRepository
}