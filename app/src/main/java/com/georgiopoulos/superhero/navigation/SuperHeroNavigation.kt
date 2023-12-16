package com.georgiopoulos.superhero.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.georgiopoulos.core.navigation.AppComposeNavigator
import com.georgiopoulos.core.navigation.SuperHeroScreens
import com.georgiopoulos.feature.details.DetailsScreen
import com.georgiopoulos.feature.home.HomeScreen

fun NavGraphBuilder.superHeroNavigation(
    composeNavigator: AppComposeNavigator,
) {
    composable(
        route = SuperHeroScreens.Home.name,
    ) { entry ->
        HomeScreen(
            composeNavigator = composeNavigator,
            isRefreshStorage = entry.savedStateHandle[SuperHeroScreens.key_refresh_storage_flag]
                ?: false
        )
    }
    composable(
        route = SuperHeroScreens.Details.name,
        arguments = SuperHeroScreens.Details.navArguments,
    ) {
        DetailsScreen(composeNavigator = composeNavigator)
    }
}
