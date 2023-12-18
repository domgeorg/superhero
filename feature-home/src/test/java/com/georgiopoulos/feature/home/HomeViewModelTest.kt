package com.georgiopoulos.feature.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DifferCallback
import androidx.paging.NullPaddedList
import androidx.paging.PagingData
import androidx.paging.PagingDataDiffer
import com.georgiopoulos.core.data.repository.home.HomeRepository
import com.georgiopoulos.core.model.superhero.SuperHeroModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = CoroutineTestRule()

    private val homeRepository: HomeRepository = mockk(relaxed = true)
    private val homeViewModel = HomeViewModel(homeRepository)

    private val antManSuperHero = SuperHeroModel(
        id = 1,
        name = "Ant-Man",
        standardImageUrl = "",
        description = ""
    )

    private val spiderManSuperHero = SuperHeroModel(
        id = 2,
        name = "Spider-Man",
        standardImageUrl = "",
        description = ""
    )

    private val ironManSuperHero = SuperHeroModel(
        id = 3,
        name = "Iron Man",
        standardImageUrl = "",
        description = ""
    )

    @Test
    fun `Given fetching superhero list from network, When data is emitted, Then update superHeroNetworkPagingData`() =
        runTest {
            // Given
            val pagingData = flow { emit(PagingData.from(listOf(antManSuperHero))) }
            coEvery { homeRepository.fetchSuperHeroListFromNetwork() } returns pagingData

            // Then
            val result = homeViewModel.superHeroNetworkPagingData.take(1).toList().first()
            assertEquals(result.collectDataForTest(), listOf(antManSuperHero))
        }

    @Test
    fun `Given fetching superhero list from storage, When data is emitted, Then update superHeroStoragePagingData`() =
        runTest {
            // Given
            val pagingData = flow { emit(PagingData.from(listOf(spiderManSuperHero))) }
            coEvery { homeRepository.fetchSuperHeroListFromStorage() } returns pagingData

            // Then
            val result = homeViewModel.superHeroStoragePagingData.take(1).toList().first()
            assertEquals(result.collectDataForTest(), listOf(spiderManSuperHero))
        }

    @Test
    fun `Given a superhero, When saving data, Then call save method in repository`() =
        runTest {
            // Given
            val superHero = ironManSuperHero

            // When
            homeViewModel.passSuperHeroData(superHero)

            // Then
            coEvery { homeRepository.save(superHero) } returns Unit
        }

    /**
     * Collects data from [PagingData] for testing purposes.
     *
     * @return A list containing the collected data.
     */
    private suspend fun <T : Any> PagingData<T>.collectDataForTest(): List<T> {
        val dcb = object : DifferCallback {
            override fun onChanged(position: Int, count: Int) {}
            override fun onInserted(position: Int, count: Int) {}
            override fun onRemoved(position: Int, count: Int) {}
        }
        val items = mutableListOf<T>()
        val dif = object : PagingDataDiffer<T>(dcb, StandardTestDispatcher()) {
            override suspend fun presentNewList(
                previousList: NullPaddedList<T>,
                newList: NullPaddedList<T>,
                lastAccessedIndex: Int,
                onListPresentable: () -> Unit
            ): Int? {
                for (idx in 0 until newList.size)
                    items.add(newList.getFromStorage(idx))
                onListPresentable()
                return null
            }
        }
        dif.collectFrom(this)
        return items
    }
}

@ExperimentalCoroutinesApi
class CoroutineTestRule(
    private val dispatcher: TestDispatcher = StandardTestDispatcher()
) : TestWatcher() {

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}