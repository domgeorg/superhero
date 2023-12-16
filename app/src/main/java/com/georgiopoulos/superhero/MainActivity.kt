package com.georgiopoulos.superhero

import android.graphics.Color.TRANSPARENT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.georgiopoulos.core.design.theme.DesignSystemTheme
import com.georgiopoulos.core.navigation.AppComposeNavigator
import com.georgiopoulos.superhero.ui.AppBaseUi
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    internal lateinit var appComposeNavigator: AppComposeNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DesignSystemTheme {
                AppBaseUi(composeNavigator = appComposeNavigator)
            }
        }
    }
}
