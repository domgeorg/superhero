package com.georgiopoulos.core.preferences.di


import com.georgiopoulos.core.preferences.PreferenceStorage
import com.georgiopoulos.core.preferences.SharedPreferencesStorage
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface PreferencesModule {

    @Binds
    fun bindsPreferenceDataStore(
        sharedPreferencesStorage: SharedPreferencesStorage,
    ): PreferenceStorage
}
