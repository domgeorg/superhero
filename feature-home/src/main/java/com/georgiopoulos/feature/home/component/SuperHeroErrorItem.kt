package com.georgiopoulos.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.georgiopoulos.core.design.DesignSystemStyle
import com.georgiopoulos.core.design.theme.DesignSystemTheme
import com.georgiopoulos.core.design.widget.icon.DesignSystemIcon
import com.georgiopoulos.core.design.widget.icon.IconSize
import com.georgiopoulos.core.design.widget.icon.IconType

@Composable
fun SuperHeroErrorItem(
    onClickAction: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(DesignSystemTheme.colors.neutralColors.neutral0)
            .height(DesignSystemTheme.spacings.spacing80)
            .clickable {
                onClickAction()
            },
        contentAlignment = Alignment.Center,
    ) {
        DesignSystemIcon(
            modifier = Modifier.align(Alignment.Center),
            iconType = IconType.RELOAD,
            iconSize = IconSize.BIG,
            style = DesignSystemStyle.DARK
        )
    }
}
