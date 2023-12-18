package com.georgiopoulos.core.data.repository.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.georgiopoulos.core.data.repository.home.SuperHeroDataSource
import com.georgiopoulos.core.database.SuperHeroDao
import com.georgiopoulos.core.database.entity.SuperHeroEntity
import com.georgiopoulos.core.database.mapper.SuperHeroEntityMapper
import com.georgiopoulos.core.model.superhero.SuperHeroModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test

class DetailsRepositoryImplTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val superHeroDao = mockk<SuperHeroDao>(relaxed = true)
    private val superHeroEntityMapper = mockk<SuperHeroEntityMapper>(relaxed = true)
    private val superHeroDataSource = mockk<SuperHeroDataSource>(relaxed = true)

    private val detailsRepository = DetailsRepositoryImpl(
        superheroDao = superHeroDao,
        supperHeroEntityMapper = superHeroEntityMapper,
        superHeroDataSource = superHeroDataSource,
        ioDispatcher = Dispatchers.Unconfined
    )

    @Test
    fun `Given isInSquad is called with a superhero name, When superhero is in the squad, Then emit true`(): Unit =
        runTest {
            // Given
            val superheroName = "Ant-Man"
            coEvery { superHeroDao.isSuperHeroInDatabase(superheroName) } returns true

            // When
            val result = detailsRepository.isInSquad(superheroName).toList()

            // Then
            assertEquals(listOf(true), result)
            coEvery { superHeroDao.isSuperHeroInDatabase(superheroName) }
        }

    @Test
    fun `Given isInSquad is called with a superhero name, When superhero is not in the squad, Then emit false`(): Unit =
        runTest {
            // Given
            val superheroName = "Iron Man"
            coEvery { superHeroDao.isSuperHeroInDatabase(superheroName) } returns false

            // When
            val result = detailsRepository.isInSquad(superheroName).toList()

            // Then
            assertEquals(listOf(false), result)
            coEvery { superHeroDao.isSuperHeroInDatabase(superheroName) }
        }

    @Test
    fun `Given addInSquad is called with a superhero, When adding the superhero to the squad, Then insert corresponding SuperHeroEntity`() =
        runTest {
            // Given
            val superheroModel = SuperHeroModel(
                id = 1,
                name = "Ant-Man",
                standardImageUrl = "",
                description = "A skilled thief and user of the Ant-Man suit, capable of shrinking in size and communicating with insects."
            )
            val superheroEntity = SuperHeroEntity(
                id = 1,
                name = "Ant-Man",
                standardImageUrl = "",
                description = "A skilled thief and user of the Ant-Man suit, capable of shrinking in size and communicating with insects."
            )
            coEvery { superHeroEntityMapper.mapEntity(superheroModel) } returns superheroEntity

            // When
            detailsRepository.addInSquad(superheroModel)

            // Then
            coEvery { superHeroEntityMapper.mapEntity(superheroModel) }
            coVerify { superHeroDao.insertSuperHero(superheroEntity) }
        }

    @Test
    fun `Given removeFromSquad is called with a superhero, When removing the superhero from the squad, Then remove corresponding SuperHeroEntity`() =
        runTest {
            // Given
            val superheroModel = SuperHeroModel(
                id = 1,
                name = "Ant-Man",
                standardImageUrl = "",
                description = "A skilled thief and user of the Ant-Man suit, capable of shrinking in size and communicating with insects."
            )
            val superheroEntity = SuperHeroEntity(
                id = 1,
                name = "Ant-Man",
                standardImageUrl = "",
                description = "A skilled thief and user of the Ant-Man suit, capable of shrinking in size and communicating with insects."
            )
            coEvery { superHeroEntityMapper.mapEntity(superheroModel) } returns superheroEntity

            // When
            detailsRepository.removeFromSquad(superheroModel)

            // Then
            coEvery { superHeroEntityMapper.mapEntity(superheroModel) }
            coVerify { superHeroDao.removeSuperHeron(superheroEntity) }
        }

    @Test
    fun `Given getSuperHero is called, When retrieving superhero data, Then return the superhero model`() {
        // Given
        val superheroModel = SuperHeroModel(
            id = 1,
            name = "Ant-Man",
            standardImageUrl = "",
            description = "A skilled thief and user of the Ant-Man suit, capable of shrinking in size and communicating with insects."
        )
        coEvery { superHeroDataSource.retrieve() } returns superheroModel

        // When
        val result = detailsRepository.getSuperHero()

        // Then
        assertEquals(superheroModel, result)
        coEvery { superHeroDataSource.retrieve() }
    }

    @Test
    fun `Given getSuperHero is called, When retrieving no superhero data, Then return null`() {
        // Given
        coEvery { superHeroDataSource.retrieve() } returns null

        // When
        val result = detailsRepository.getSuperHero()

        // Then
        assertNull(result)
        coEvery { superHeroDataSource.retrieve() }
    }

    @Test
    fun `Given clearSuperHeroData is called, When clearing superhero data, Then call SuperHeroDataSource remove`() {
        // When
        detailsRepository.clearSuperHeroData()

        // Then
        verify { superHeroDataSource.remove() }
    }
}
