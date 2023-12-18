package com.georgiopoulos.core.data.repository.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.georgiopoulos.core.data.DataConstants
import com.georgiopoulos.core.model.error.PaginationException
import com.georgiopoulos.core.model.superhero.SuperHeroModel
import com.georgiopoulos.core.network.mapper.error.ErrorMapper
import com.georgiopoulos.core.network.mapper.heros.SuperHeroMapper
import com.georgiopoulos.core.network.service.Service

/**
 * Paging source for loading superhero data from a network service using the Paging library.
 *
 * This class extends [PagingSource] and is responsible for loading chunks of superhero data
 * for paginated display in a RecyclerView.
 *
 * @param service The network service responsible for fetching superhero data.
 * @param superHeroMapper The mapper responsible for transforming network response to domain model.
 * @param errorMapper The mapper responsible for handling network errors and mapping them to domain errors.
 */
internal class SuperHeroPagingNetworkDataSource(
    private val service: Service,
    private val superHeroMapper: SuperHeroMapper,
    private val errorMapper: ErrorMapper,
) : PagingSource<Int, SuperHeroModel>() {

    /**
     * Returns the refresh key for the given [PagingState].
     *
     * The refresh key is used to determine when to invalidate and re-fetch the data.
     */
    override fun getRefreshKey(state: PagingState<Int, SuperHeroModel>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.run {
                prevKey?.plus(1) ?: nextKey?.minus(1)
            }
        }

    /**
     * Loads a chunk of superhero data for the given [LoadParams].
     *
     * This function is called by the Paging library to load data for a specific page.
     *
     * @param params The load parameters, including the requested key (page number).
     * @return A [LoadResult] containing the loaded data or an error.
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SuperHeroModel> =
        try {
            val page = params.key ?: DataConstants.STARTING_PAGE_INDEX
            val response = service.fetchSuperHeroList(offset = page * DataConstants.PAGING_SIZE)

            LoadResult.Page(
                data = superHeroMapper.mapSuperHero(response),
                prevKey = if (page == DataConstants.STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (response.data.results.isEmpty()) null else page.plus(1),
            )
        } catch (exception: Exception) {
            LoadResult.Error(
                throwable = PaginationException(
                    errorModel = errorMapper.mapError(exception),
                ),
            )
        }
}
