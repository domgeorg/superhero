package com.georgiopoulos.core.data.repository.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.georgiopoulos.core.data.DataConstants
import com.georgiopoulos.core.database.SuperHeroDao
import com.georgiopoulos.core.database.entity.SuperHeroEntity
import com.georgiopoulos.core.database.mapper.SuperHeroEntityMapper
import com.georgiopoulos.core.model.error.ErrorModel
import com.georgiopoulos.core.model.error.PaginationException
import com.georgiopoulos.core.model.superhero.SuperHeroModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.coroutines.cancellation.CancellationException

class SuperHeroPagingDatabaseDataSourceTest {

    private val superHeroDao: SuperHeroDao = mockk(relaxed = true)
    private val superHeroEntityMapper: SuperHeroEntityMapper = mockk(relaxed = true)

    private val dataSource = SuperHeroPagingDatabaseDataSource(
        superHeroDao = superHeroDao,
        superHeroEntityMapper = superHeroEntityMapper,
    )

    @Test
    fun `Given a successful database response with empty data on the last page, When load is called, Then it should return a LoadResult Page`() =
        runTest {
            // Given
            val superHeroList = createFakeSuperHeroModels()
            coEvery {
                superHeroDao.getSuperHeroList(
                    limit = DataConstants.PAGING_SIZE,
                    offset = DataConstants.PAGING_SIZE,
                )
            } returns createFakeSuperHeroEntities()
            every { superHeroEntityMapper.mapModel(any()) } returns superHeroList.first()

            val params = createFakeLoadParams()

            // When
            val result = dataSource.load(params)

            // Then
            assertEquals(
                /* expected = */
                PagingSource.LoadResult.Page(
                    data = superHeroList,
                    prevKey = 0,
                    nextKey = 2,
                ),
                /* actual = */ result,
            )
        }

    @Test
    fun `Given a successful database response, When load is called, Then it should return a LoadResult Page`() =
        runTest {
            // Given
            coEvery {
                superHeroDao.getSuperHeroList(
                    limit = DataConstants.PAGING_SIZE,
                    offset = DataConstants.PAGING_SIZE,
                )
            } returns createFakeEmptySuperHeroEntities()
            every {
                superHeroEntityMapper.mapModel(any())
            } returns createFakeSuperHeroModels().first()

            val params = createFakeLoadParams()

            // When
            val result = dataSource.load(params)

            // Then
            assertEquals(
                /* expected = */
                PagingSource.LoadResult.Page(
                    data = emptyList(),
                    prevKey = 0,
                    nextKey = null,
                ),
                /* actual = */ result,
            )
        }

    @Test
    fun `Given an exception during database call, When load is called, Then it should return a LoadResult Error`() =
        runTest {
            // Given
            coEvery {
                superHeroDao.getSuperHeroList(
                    limit = DataConstants.PAGING_SIZE,
                    offset = DataConstants.PAGING_SIZE,
                )
            } throws Exception("Database Error")

            val params = createFakeLoadParams()

            // When
            val result = dataSource.load(params)

            // Then
            assertEquals(
                /* expected = */
                PagingSource.LoadResult.Error<Int, SuperHeroModel>(
                    throwable = PaginationException(
                        errorModel = ErrorModel.UnknownErrorModel,
                    ),
                ),
                /* actual = */ result,
            )
        }

    @Test
    fun `Given a cancelled coroutine, When load is called, Then it should return a LoadResult Error`() =
        runTest {
            // Given
            coEvery {
                superHeroDao.getSuperHeroList(
                    limit = DataConstants.PAGING_SIZE,
                    offset = DataConstants.PAGING_SIZE,
                )
            } throws CancellationException()

            val params = createFakeLoadParams()

            // When
            val result = dataSource.load(params)

            // Then
            assertEquals(
                /* expected = */
                PagingSource.LoadResult.Error<Int, SuperHeroModel>(
                    throwable = PaginationException(
                        errorModel = ErrorModel.UnknownErrorModel,
                    ),
                ),
                /* actual = */ result,
            )
        }

    @Test
    fun `Given a starting page, When getRefreshKey is called, Then it should return null`() {
        // Given
        val pagingState = mockk<PagingState<Int, SuperHeroModel>>()

        every { pagingState.anchorPosition } returns 0
        every { pagingState.closestPageToPosition(any()) } returns null

        // When
        val result = dataSource.getRefreshKey(pagingState)

        // Then
        assertEquals(null, result)
    }

    @Test
    fun `Given a valid paging state, When getRefreshKey is called, Then it should return the next key`() {
        // Given
        val pagingState = mockk<PagingState<Int, SuperHeroModel>>()

        every { pagingState.anchorPosition } returns 10
        every { pagingState.closestPageToPosition(any()) } returns createFakeLoadResultPage()

        // When
        val result = dataSource.getRefreshKey(pagingState)

        // Then
        assertEquals(1, result)
    }

    private fun createFakeSuperHeroEntities(): List<SuperHeroEntity> {
        return listOf(
            SuperHeroEntity(
                id = 1,
                name = "Ant-man",
                standardImageUrl = "path.jpg",
                description = "Description",
            )
        )
    }

    private fun createFakeSuperHeroModels(): List<SuperHeroModel> {
        return listOf(
            SuperHeroModel(
                id = 1,
                name = "Ant-man",
                standardImageUrl = "path.jpg",
                description = "Description",
            )
        )
    }

    private fun createFakeLoadParams(): PagingSource.LoadParams<Int> {
        return PagingSource.LoadParams.Refresh(
            key = 1,
            loadSize = DataConstants.PAGING_SIZE,
            placeholdersEnabled = false,
        )
    }

    private fun createFakeLoadResultPage(): PagingSource.LoadResult.Page<Int, SuperHeroModel> {
        return PagingSource.LoadResult.Page(
            data = createFakeSuperHeroModels(),
            prevKey = null,
            nextKey = 2,
        )
    }

    private fun createFakeEmptySuperHeroEntities(): List<SuperHeroEntity> {
        return emptyList()
    }
}
