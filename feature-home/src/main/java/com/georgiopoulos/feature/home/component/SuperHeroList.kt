package com.georgiopoulos.feature.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.georgiopoulos.core.design.theme.DesignSystemTheme
import com.georgiopoulos.core.design.widget.loader.DesignSystemLoader
import com.georgiopoulos.core.model.error.getErrorMessageResId
import com.georgiopoulos.core.model.superhero.SuperHeroModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun SuperHeroList(
    modifier: Modifier = Modifier,
    pagingItems: LazyPagingItems<SuperHeroModel>,
    onClickAction: (SuperHeroModel) -> Unit,
) {
    Box(
        modifier = modifier,
    ) {

        SwipeRefresh(
            state = rememberSwipeRefreshState(false),
            onRefresh = { pagingItems.refresh() },
        ) {

            LazyColumn {

                items(pagingItems.itemCount) { index ->
                    SuperHeroItem(
                        hero = pagingItems[index]!!,
                        onClickAction = { superHero ->
                            onClickAction(superHero)
                        },
                    )
                }


                when (pagingItems.loadState.append) {
                    is LoadState.Loading -> item {
                        DesignSystemLoader(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(DesignSystemTheme.spacings.spacing56)
                                .align(Alignment.Center)
                        )
                    }

                    is LoadState.Error -> item {
                        SuperHeroErrorItem {
                            pagingItems.retry()
                        }
                    }

                    is LoadState.NotLoading -> {
                        /* Do-nothing */
                    }
                }
            }


            when (val refreshState = pagingItems.loadState.refresh) {
                is LoadState.Loading -> DesignSystemLoader(
                    modifier = Modifier.fillMaxSize(),
                )

                is LoadState.Error -> SuperHeroLoadingError(
                    modifier = Modifier.fillMaxSize(),
                    messageResId = refreshState.error.getErrorMessageResId()
                ) {
                    pagingItems.refresh()
                }

                is LoadState.NotLoading -> {
                    /* Do-nothing */
                }
            }
        }
    }
}
