package com.georgiopoulos.core.database.di

import com.georgiopoulos.core.database.mapper.SuperHeroEntityMapper
import com.georgiopoulos.core.database.mapper.SuperHeroEntityMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface MapperModule {

    @Binds
    fun bindsSuperHeroEntityMapper(
        superHeroEntityMapperImpl: SuperHeroEntityMapperImpl,
    ): SuperHeroEntityMapper
}