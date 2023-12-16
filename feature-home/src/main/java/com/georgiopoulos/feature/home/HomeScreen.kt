package com.georgiopoulos.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.georgiopoulos.core.design.theme.DesignSystemTheme
import com.georgiopoulos.core.navigation.AppComposeNavigator
import com.georgiopoulos.core.navigation.SuperHeroScreens
import com.georgiopoulos.feature.home.component.AppBar
import com.georgiopoulos.feature.home.component.MySquad
import com.georgiopoulos.feature.home.component.SuperHeroList

@Composable
fun HomeScreen(
    composeNavigator: AppComposeNavigator,
    isRefreshStorage: Boolean = false,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DesignSystemTheme.colors.neutralColors.neutral0),
    ) {

        AppBar()

        MySquad(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = DesignSystemTheme.spacings.spacing8,
                    top = DesignSystemTheme.spacings.spacing16,
                    bottom = DesignSystemTheme.spacings.spacing16,
                ),
            pagingItems = viewModel.superHeroStoragePagingData.collectAsLazyPagingItems(),
            isRefreshStorage = isRefreshStorage,
            onClickAction = { superHero ->
                viewModel.passSuperHeroData(superHero)
                composeNavigator.navigate(SuperHeroScreens.Details.route)
            }
        )

        SuperHeroList(
            pagingItems = viewModel.superHeroNetworkPagingData.collectAsLazyPagingItems(),
            onClickAction = { superHero ->
                viewModel.passSuperHeroData(superHero)
                composeNavigator.navigate(SuperHeroScreens.Details.route)
            }
        )
    }
}
