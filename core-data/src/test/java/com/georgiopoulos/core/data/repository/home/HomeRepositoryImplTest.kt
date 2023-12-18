package com.georgiopoulos.core.data.repository.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.Pager
import androidx.paging.PagingData
import com.georgiopoulos.core.model.superhero.SuperHeroModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class HomeRepositoryImplTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val superHeroNetworkPager = mockk<Pager<Int, SuperHeroModel>>(relaxed = true)
    private val superHeroDatabasePager = mockk<Pager<Int, SuperHeroModel>>(relaxed = true)
    private val superHeroDataSource = mockk<SuperHeroDataSource>(relaxed = true)

    private val homeRepository = HomeRepositoryImpl(
        superHeroNetworkPager = superHeroNetworkPager,
        superHeroDatabasePager = superHeroDatabasePager,
        superHeroDataSource = superHeroDataSource,
        ioDispatcher = Dispatchers.Unconfined,
    )

    @Test
    fun `Given fetchSuperHeroListFromNetwork is called, When data is available from network, Then return PagingData from network`() =
        runBlocking {
            // Given
            val pagingData = PagingData.from(
                listOf(
                    SuperHeroModel(
                        id = 1,
                        name = "Ant-Man",
                        standardImageUrl = "ant_man.jpg",
                        description = "A skilled thief and user of the Ant-Man suit, capable of shrinking in size and communicating with insects.",
                    )
                )
            )
            coEvery { superHeroNetworkPager.flow.flowOn(Dispatchers.Unconfined) } returns flowOf(
                pagingData
            )

            // When
            val result = homeRepository.fetchSuperHeroListFromNetwork()

            // Then
            result.collect { collectedPagingData ->
                assertEquals(pagingData, collectedPagingData)
            }
        }

    @Test
    fun `Given fetchSuperHeroListFromStorage is called, When data is available from storage, Then return PagingData from storage`() =
        runBlocking {
            // Given
            val pagingData = PagingData.from(
                listOf(
                    SuperHeroModel(
                        id = 1,
                        name = "Ant-Man",
                        standardImageUrl = "ant_man.jpg",
                        description = "A skilled thief and user of the Ant-Man suit, capable of shrinking in size and communicating with insects.",
                    )
                )
            )
            coEvery { superHeroDatabasePager.flow.flowOn(Dispatchers.Unconfined) } returns flowOf(
                pagingData
            )

            // When
            val result = homeRepository.fetchSuperHeroListFromStorage()

            // Then
            result.collect { collectedPagingData ->
                assertEquals(pagingData, collectedPagingData)
            }
        }

    @Test
    fun `Given save is called with a SuperHeroModel, When saving the SuperHeroModel, Then call SuperHeroDataSource save`() {
        // Given
        val superHeroModel = SuperHeroModel(
            id = 1,
            name = "Ant-Man",
            standardImageUrl = "ant_man.jpg",
            description = "A skilled thief and user of the Ant-Man suit, capable of shrinking in size and communicating with insects.",
        )
        coEvery { superHeroDataSource.save(superHeroModel) } returns Unit

        // When
        homeRepository.save(superHeroModel)

        // Then
        coEvery { superHeroDataSource.save(superHeroModel) }
    }
}
