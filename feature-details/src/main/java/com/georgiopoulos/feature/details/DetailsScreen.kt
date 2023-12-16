package com.georgiopoulos.feature.details

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.georgiopoulos.core.design.DesignSystemStyle
import com.georgiopoulos.core.design.theme.DesignSystemTheme
import com.georgiopoulos.core.design.widget.button.DesignSystemButtonPrimary
import com.georgiopoulos.core.design.widget.icon.DesignSystemIcon
import com.georgiopoulos.core.design.widget.icon.IconType
import com.georgiopoulos.core.design.widget.loader.DesignSystemLoader
import com.georgiopoulos.core.design.widget.text.DesignSystemText
import com.georgiopoulos.core.design.widget.text.TextType
import com.georgiopoulos.core.navigation.AppComposeNavigator
import com.georgiopoulos.core.navigation.SuperHeroScreens
import com.georgiopoulos.feature.details.DetailsEvent.AddInSquad
import com.georgiopoulos.feature.details.DetailsEvent.DismissError
import com.georgiopoulos.feature.details.DetailsEvent.RemoveFromSquad
import com.georgiopoulos.feature.details.DetailsEvent.RemoveFromSquadIntention
import com.georgiopoulos.feature.details.component.AreYouSureDialog
import com.georgiopoulos.feature.details.component.ErrorDialog

@Composable
fun DetailsScreen(
    composeNavigator: AppComposeNavigator,
    viewModel: DetailsViewModel = hiltViewModel(),
) {
    val state = viewModel.uiState.collectAsState()

    BackHandler {
        onNavigateBack(
            composeNavigator = composeNavigator,
            squadChangeRequested = state.value.squadChangeRequested,
        )
    }

    if (state.value.isLoading) {
        DesignSystemLoader(
            modifier = Modifier.fillMaxSize(),
        )
    } else {

        when {
            state.value.displayAreYouSureDialog -> AreYouSureDialog(
                onDismiss = {
                    viewModel.triggerEvent(
                        event = RemoveFromSquadIntention(isDismissAction = true),
                    )
                },
                onRemove = {
                    viewModel.triggerEvent(
                        event = RemoveFromSquad,
                    )
                },
            )

            state.value.displayErrorDialog -> ErrorDialog {
                viewModel.triggerEvent(
                    event = DismissError,
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DesignSystemTheme.colors.neutralColors.neutral0)
                .verticalScroll(rememberScrollState())
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(400.dp),
                    model = state.value.superHero.standardImageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                DesignSystemIcon(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(DesignSystemTheme.spacings.spacing16)
                        .clickable {
                            onNavigateBack(
                                composeNavigator = composeNavigator,
                                squadChangeRequested = state.value.squadChangeRequested,
                            )
                        },
                    iconType = IconType.EXIT,
                    style = DesignSystemStyle.LIGHT,
                )
            }

            DesignSystemText(
                modifier = Modifier
                    .padding(
                        horizontal = DesignSystemTheme.spacings.spacing16,
                        vertical = DesignSystemTheme.spacings.spacing32,
                    ),
                text = state.value.superHero.name,
                textType = TextType.TITLE_1,
            )

            if (state.value.displayHireFireButton) {
                DesignSystemButtonPrimary(
                    modifier = Modifier
                        .padding(horizontal = DesignSystemTheme.spacings.spacing16),
                    text = stringResource(id = state.value.hireFireButtonTextResId),
                    destructive = state.value.isInSquad
                ) {
                    viewModel.triggerEvent(
                        event = if (state.value.isInSquad) RemoveFromSquadIntention() else AddInSquad,
                    )
                }
            }

            DesignSystemText(
                modifier = Modifier
                    .padding(
                        horizontal = DesignSystemTheme.spacings.spacing16,
                        vertical = DesignSystemTheme.spacings.spacing32,
                    ),
                text = state.value.superHero.description,
            )
        }
    }
}

private fun onNavigateBack(
    composeNavigator: AppComposeNavigator,
    squadChangeRequested: Boolean,
) {
    composeNavigator.navigateBackWithResult(
        key = SuperHeroScreens.key_refresh_storage_flag,
        result = squadChangeRequested,
        route = SuperHeroScreens.Home.route,
    )
}