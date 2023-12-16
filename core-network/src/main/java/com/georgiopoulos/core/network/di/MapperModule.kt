package com.georgiopoulos.core.network.di

import com.georgiopoulos.core.network.mapper.error.ErrorMapper
import com.georgiopoulos.core.network.mapper.error.ErrorMapperImpl
import com.georgiopoulos.core.network.mapper.heros.SuperHeroMapper
import com.georgiopoulos.core.network.mapper.heros.SuperHeroMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface MapperModule {

    @Binds
    fun bindsSuperHeroMapper(
        superHeroMapperImpl: SuperHeroMapperImpl,
    ): SuperHeroMapper

    @Binds
    fun bindsErrorMapper(
        errorMapperImpl: ErrorMapperImpl,
    ): ErrorMapper
}
