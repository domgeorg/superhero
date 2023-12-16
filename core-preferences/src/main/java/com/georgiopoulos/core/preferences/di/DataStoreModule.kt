package com.georgiopoulos.core.preferences.di

import android.content.Context
import android.content.SharedPreferences
import com.georgiopoulos.core.preferences.PreferencesConstants.SUPER_HERO_PREFERENCES
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal object DataStoreModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences(SUPER_HERO_PREFERENCES, Context.MODE_PRIVATE)
    }
}
