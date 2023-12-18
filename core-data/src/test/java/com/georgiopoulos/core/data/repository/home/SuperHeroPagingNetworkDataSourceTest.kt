package com.georgiopoulos.core.data.repository.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.georgiopoulos.core.data.DataConstants
import com.georgiopoulos.core.model.error.ErrorModel
import com.georgiopoulos.core.model.error.PaginationException
import com.georgiopoulos.core.model.superhero.SuperHeroModel
import com.georgiopoulos.core.network.mapper.error.ErrorMapper
import com.georgiopoulos.core.network.mapper.heros.SuperHeroMapper
import com.georgiopoulos.core.network.response.HeroThumbnail
import com.georgiopoulos.core.network.response.NetworkResults
import com.georgiopoulos.core.network.response.SuperHeroNetwork
import com.georgiopoulos.core.network.response.SuperHeroNetworkResponse
import com.georgiopoulos.core.network.service.Service
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.coroutines.cancellation.CancellationException

class SuperHeroPagingNetworkDataSourceTest {

    private val service: Service = mockk(relaxed = true)
    private val superHeroMapper: SuperHeroMapper = mockk(relaxed = true)
    private val errorMapper: ErrorMapper = mockk(relaxed = true)

    private val dataSource = SuperHeroPagingNetworkDataSource(
        service = service,
        superHeroMapper = superHeroMapper,
        errorMapper = errorMapper,
    )

    @Test
    fun `Given a successful network response with empty data on the last page, When load is called, Then it should return a LoadResult Page`() =
        runTest {
            // Given
            val superHeroList = createFakeSuperHeroModels()
            coEvery { service.fetchSuperHeroList(offset = DataConstants.PAGING_SIZE) } returns createFakeSuperHeroNetworkResponse()
            every { superHeroMapper.mapSuperHero(any()) } returns superHeroList

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
    fun `Given a successful network response, When load is called, Then it should return a LoadResult Page`() =
        runTest {
            // Given
            coEvery { service.fetchSuperHeroList(offset = DataConstants.PAGING_SIZE) } returns createFakeEmptySuperHeroNetworkResponse()
            every { superHeroMapper.mapSuperHero(any()) } returns emptyList()

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
    fun `Given an exception during network call, When load is called, Then it should return a LoadResult Error`() =
        runTest {
            // Given
            coEvery {
                service.fetchSuperHeroList(offset = DataConstants.PAGING_SIZE)
            } throws Exception("Network Error")
            every { errorMapper.mapError(any()) } returns createFakeErrorModel()

            val params = createFakeLoadParams()

            // When
            val result = dataSource.load(params)

            // Then
            assertEquals(
                /* expected = */
                PagingSource.LoadResult.Error<Int, SuperHeroModel>(
                    throwable = PaginationException(
                        errorModel = createFakeErrorModel(),
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
                service.fetchSuperHeroList(offset = DataConstants.PAGING_SIZE)
            } throws CancellationException()
            every { errorMapper.mapError(any()) } returns createFakeErrorModel()

            val params = createFakeLoadParams()

            // When
            val result = dataSource.load(params)

            // Then
            assertEquals(
                /* expected = */
                PagingSource.LoadResult.Error<Int, SuperHeroModel>(
                    throwable = PaginationException(
                        errorModel = createFakeErrorModel(),
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
        assertEquals(2, result)
    }

    private fun createFakeSuperHeroNetworkResponse(): SuperHeroNetworkResponse {
        return SuperHeroNetworkResponse(
            data = NetworkResults(
                results = listOf(
                    SuperHeroNetwork(
                        id = 1,
                        name = "Ant-man",
                        description = "Description",
                        thumbnail = HeroThumbnail("path", "jpg"),
                    )
                )
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

    private fun createFakeErrorModel(): ErrorModel {
        return ErrorModel.UnknownErrorModel
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
            prevKey = 1,
            nextKey = 3,
        )
    }

    private fun createFakeEmptySuperHeroNetworkResponse(): SuperHeroNetworkResponse {
        return SuperHeroNetworkResponse(NetworkResults(emptyList()))
    }
}
