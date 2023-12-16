package com.georgiopoulos.feature.home.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import com.georgiopoulos.core.design.theme.DesignSystemTheme

@Composable
fun CircleSuperHeroImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    size: Dp,
) {

    AsyncImage(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .border(
                width = DesignSystemTheme.spacings.spacing2,
                color = DesignSystemTheme.colors.neutralColors.neutral2,
                shape = CircleShape
            ),
        model = imageUrl,
        contentDescription = null,
    )
}
