package com.georgiopoulos.feature.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState.Error
import androidx.paging.LoadState.Loading
import androidx.paging.LoadState.NotLoading
import androidx.paging.compose.LazyPagingItems
import com.georgiopoulos.core.design.theme.DesignSystemTheme
import com.georgiopoulos.core.design.widget.loader.DesignSystemLoader
import com.georgiopoulos.core.design.widget.text.DesignSystemText
import com.georgiopoulos.core.design.widget.text.TextType
import com.georgiopoulos.core.model.superhero.SuperHeroModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.georgiopoulos.core_resources.R as Resources

@Composable
fun MySquad(
    modifier: Modifier = Modifier,
    isRefreshStorage: Boolean = false,
    pagingItems: LazyPagingItems<SuperHeroModel>,
    onClickAction: (SuperHeroModel) -> Unit,
) {

    LaunchedEffect(isRefreshStorage) {
        if (isRefreshStorage) {
            launch(Dispatchers.Main) {
                pagingItems.refresh()
            }
        }
    }

    when (pagingItems.loadState.refresh) {
        is Error -> return
        Loading -> DesignSystemLoader(
            modifier = Modifier
                .fillMaxWidth()
                .height(DesignSystemTheme.spacings.spacing120),
        )

        is NotLoading -> { /* Do nothing */
        }
    }

    if (pagingItems.itemCount == 0) {
        return
    }

    Column(
        modifier = modifier,
    ) {

        DesignSystemText(
            modifier = Modifier
                .padding(
                    start = DesignSystemTheme.spacings.spacing8,
                    bottom = DesignSystemTheme.spacings.spacing16,
                ),
            text = stringResource(id = Resources.string.my_squad),
            textType = TextType.TITLE_2,
        )

        LazyRow {

            items(pagingItems.itemCount) { index ->
                MySquadItem(
                    hero = pagingItems[index]!!,
                    onClickAction = { superHero ->
                        onClickAction(superHero)
                    },
                )
            }

            when (pagingItems.loadState.append) {
                is Loading -> item {
                    DesignSystemLoader(
                        modifier = Modifier
                            .size(DesignSystemTheme.spacings.spacing120),
                    )
                }

                is Error -> item {
                    MySquadErrorItem {
                        pagingItems.retry()
                    }
                }

                is NotLoading -> {
                    /* Do-nothing */
                }
            }
        }
    }
}
