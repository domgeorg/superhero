package com.georgiopoulos.core.data.repository.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.georgiopoulos.core.data.DataConstants
import com.georgiopoulos.core.database.SuperHeroDao
import com.georgiopoulos.core.database.mapper.SuperHeroEntityMapper
import com.georgiopoulos.core.model.error.ErrorModel
import com.georgiopoulos.core.model.error.PaginationException
import com.georgiopoulos.core.model.superhero.SuperHeroModel
import kotlinx.coroutines.isActive
import kotlin.coroutines.cancellation.CancellationException
import kotlin.coroutines.coroutineContext

/**
 * A {@link PagingSource} implementation responsible for loading paginated data from a local database
 * for superhero-related content. It serves as a data source for the Paging 3 library, allowing seamless
 * integration with UI components, such as RecyclerView, in a paginated manner.
 *
 * @param superHeroDao The Data Access Object (DAO) for superhero-related database operations.
 * @param superHeroEntityMapper The mapper responsible for converting {@link SuperHeroEntity} instances
 *                              from the database to {@link SuperHeroModel} instances for presentation.
 *
 * @see PagingSource
 */
internal class SuperHeroPagingDatabaseDataSource(
    private val superHeroDao: SuperHeroDao,
    private val superHeroEntityMapper: SuperHeroEntityMapper,
) : PagingSource<Int, SuperHeroModel>() {

    /**
     * Retrieves the refresh key based on the current paging state.
     *
     * @param state The current {@link PagingState}.
     * @return The refresh key as an {@link Integer}, or null if unavailable.
     */
    override fun getRefreshKey(state: PagingState<Int, SuperHeroModel>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.run {
                prevKey?.plus(1) ?: nextKey?.minus(1)
            }
        }

    /**
     * Loads a page of superhero data from the local database based on the provided {@link LoadParams}.
     *
     * @param params The parameters for loading data, including the page number.
     * @return A {@link LoadResult} containing the loaded data, or an error if loading fails.
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SuperHeroModel> =
        try {
            val page = params.key ?: DataConstants.STARTING_PAGE_INDEX
            val storedSuperHeroList = superHeroDao.getSuperHeroList(
                limit = DataConstants.PAGING_SIZE,
                offset = page * DataConstants.PAGING_SIZE,
            )

            val superHeroList = storedSuperHeroList.map { entity ->
                superHeroEntityMapper.mapModel(entity)
            }

            LoadResult.Page(
                data = superHeroList,
                prevKey = if (page == DataConstants.STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (superHeroList.isEmpty()) null else page.plus(1),
            )
        } catch (exception: Exception) {
            if (coroutineContext.isActive) {
                LoadResult.Error(
                    throwable = PaginationException(
                        errorModel = ErrorModel.UnknownErrorModel,
                    ),
                )
            } else {
                LoadResult.Error(throwable = CancellationException())
            }
        }
}
