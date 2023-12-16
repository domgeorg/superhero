package com.georgiopoulos.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.georgiopoulos.core.design.theme.DesignSystemTheme
import com.georgiopoulos.core.design.widget.text.DesignSystemText
import com.georgiopoulos.core.model.superhero.SuperHeroModel

@Composable
internal fun SuperHeroItem(
    hero: SuperHeroModel,
    onClickAction: (SuperHeroModel) -> Unit,
) {
    Box(
        modifier = Modifier
            .background(DesignSystemTheme.colors.neutralColors.neutral0)
            .fillMaxWidth()
            .padding(
                start = DesignSystemTheme.spacings.spacing16,
                top = DesignSystemTheme.spacings.spacing16,
                bottom = DesignSystemTheme.spacings.spacing16,
            )
            .clickable {
                onClickAction(hero)
            },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircleSuperHeroImage(
                modifier = Modifier.padding(end = DesignSystemTheme.spacings.spacing16),
                imageUrl = hero.standardImageUrl,
                size = DesignSystemTheme.spacings.spacing64,
            )

            DesignSystemText(
                text = hero.name,
            )
        }

        HorizontalDivider(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .padding(start = DesignSystemTheme.spacings.spacing80),
            color = DesignSystemTheme.colors.secondaryColors.lightBlue
        )
    }
}
