package com.georgiopoulos.core.data.repository.home

import com.georgiopoulos.core.model.superhero.SuperHeroModel
import com.georgiopoulos.core.preferences.PreferenceKey
import com.georgiopoulos.core.preferences.PreferenceStorage
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import timber.log.Timber
import javax.inject.Inject

internal class SuperHeroDataSource @Inject constructor(
    private val preferenceStorage: PreferenceStorage,
    private val moshi: Moshi,
) {

    private val superHeroAdapter by lazy {
        moshi.newBuilder()
            .add(KotlinJsonAdapterFactory())
            .build()
            .adapter(SuperHeroModel::class.java)
    }

    fun save(superHero: SuperHeroModel) {
        val json = superHeroAdapter.toJson(superHero)
        preferenceStorage.replacePreference(PreferenceKey.SUPER_HERO, json)
    }

    fun retrieve(): SuperHeroModel? {
        val json = preferenceStorage.getPreference(
            key = PreferenceKey.SUPER_HERO,
            defaultValue = "",
        )

        return try {
            superHeroAdapter.fromJson(json)
        } catch (exception: Exception) {
            Timber.e(exception)
            null
        }
    }

    fun remove() {
        preferenceStorage.removePreference(PreferenceKey.SUPER_HERO)
    }
}
