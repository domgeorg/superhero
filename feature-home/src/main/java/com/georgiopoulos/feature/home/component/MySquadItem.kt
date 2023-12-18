package com.georgiopoulos.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.georgiopoulos.core.design.theme.DesignSystemTheme
import com.georgiopoulos.core.design.widget.text.DesignSystemText
import com.georgiopoulos.core.design.widget.text.MaxLinesConfig
import com.georgiopoulos.core.design.widget.text.TextType
import com.georgiopoulos.core.model.superhero.SuperHeroModel

@Composable
internal fun MySquadItem(
    hero: SuperHeroModel,
    onClickAction: (SuperHeroModel) -> Unit,
) {
    Column(
        modifier = Modifier
            .height(DesignSystemTheme.spacings.spacing124)
            .width(DesignSystemTheme.spacings.spacing112)
            .background(DesignSystemTheme.colors.neutralColors.neutral0)
            .padding(DesignSystemTheme.spacings.spacing8)
            .clickable {
                onClickAction(hero)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CircleSuperHeroImage(
            modifier = Modifier.padding(end = DesignSystemTheme.spacings.spacing8),
            imageUrl = hero.standardImageUrl,
            size = DesignSystemTheme.spacings.spacing88,
        )

        DesignSystemText(
            text = hero.name,
            textType = TextType.BODY_2,
            overflow = TextOverflow.Ellipsis,
            maxLines = MaxLinesConfig.ForcedMaxLines(1),
        )
    }
}
