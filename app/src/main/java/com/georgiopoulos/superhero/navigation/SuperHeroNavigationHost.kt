package com.georgiopoulos.superhero.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.georgiopoulos.core.navigation.AppComposeNavigator
import com.georgiopoulos.core.navigation.SuperHeroScreens

@Composable
fun SuperHeroNavigationHost(
    navHostController: NavHostController,
    composeNavigator: AppComposeNavigator,
) {
    NavHost(
        navController = navHostController,
        startDestination = SuperHeroScreens.Home.name,
    ) {
        superHeroNavigation(
            composeNavigator = composeNavigator,
        )
    }
}
