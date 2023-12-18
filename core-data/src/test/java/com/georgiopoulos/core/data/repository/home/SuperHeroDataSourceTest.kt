package com.georgiopoulos.core.data.repository.home

import android.annotation.SuppressLint
import com.georgiopoulos.core.model.superhero.SuperHeroModel
import com.georgiopoulos.core.preferences.PreferenceKey
import com.georgiopoulos.core.preferences.PreferenceStorage
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class SuperHeroDataSourceTest {

    private val preferenceStorage: PreferenceStorage = mockk(relaxed = true)
    private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val superHeroDataSource = SuperHeroDataSource(preferenceStorage, moshi)

    private val superHeroModel = SuperHeroModel(
        id = 1,
        name = "Iron Man",
        standardImageUrl = "iron_man.jpg",
        description = "Genius, billionaire, playboy, philanthropist."
    )

    private val json =
        "{\"id\":1,\"name\":\"Iron Man\",\"standardImageUrl\":\"iron_man.jpg\",\"description\":\"Genius, billionaire, playboy, philanthropist.\"}"


    @Test
    fun `Given a SuperHeroModel, When save is called, Then it should save the model to SharedPreferences`() {
        // When
        superHeroDataSource.save(superHeroModel)

        // Then
        verify(exactly = 1) { preferenceStorage.replacePreference(PreferenceKey.SUPER_HERO, json) }
    }

    @Test
    fun `Given no data saved, When retrieve is called, Then it should return null`() {
        // Given
        every { preferenceStorage.getPreference(PreferenceKey.SUPER_HERO, "") } returns ""

        // When
        val result = superHeroDataSource.retrieve()

        // Then
        assertNull(result)
    }

    @Test
    fun `Given valid JSON data saved, When retrieve is called, Then it should return the SuperHeroModel`() {
        // Given
        every { preferenceStorage.getPreference(PreferenceKey.SUPER_HERO, "") } returns json

        // When
        val result = superHeroDataSource.retrieve()

        // Then
        assertEquals(superHeroModel, result)
    }

    @SuppressLint("CheckResult")
    @Test
    fun `Given an exception during JSON parsing, When retrieve is called, Then it should return null`() {
        // Given
        every {
            preferenceStorage.getPreference(
                PreferenceKey.SUPER_HERO,
                ""
            )
        } returns "invalid json"

        // Create a real Moshi instance
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        val superHeroDataSource = SuperHeroDataSource(preferenceStorage, moshi)

        // When
        val result = superHeroDataSource.retrieve()

        // Then
        assertNull(result)

        // Verify the calls without specifying order
        verify { preferenceStorage.getPreference(PreferenceKey.SUPER_HERO, "") }
    }


    @Test
    fun `Given SuperHeroModel saved, When remove is called, Then it should remove the data from SharedPreferences`() {
        // When
        superHeroDataSource.remove()

        // Then
        verify(exactly = 1) { preferenceStorage.removePreference(PreferenceKey.SUPER_HERO) }
    }
}