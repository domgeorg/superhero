package com.georgiopoulos.core.navigation.di

import com.georgiopoulos.core.navigation.AppComposeNavigator
import com.georgiopoulos.core.navigation.ComposeNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger module that provides dependencies related to navigation.
 */
@Module
@InstallIn(SingletonComponent::class)
internal abstract class NavigationModule {

    /**
     * Binds the implementation of [AppComposeNavigator] to be provided as a dependency.
     *
     * @param composeNavigator The implementation of [AppComposeNavigator] to be bound.
     * @return The bound instance of [AppComposeNavigator].
     */
    @Binds
    @Singleton
    abstract fun provideComposeNavigator(
        composeNavigator: ComposeNavigator,
    ): AppComposeNavigator
}
