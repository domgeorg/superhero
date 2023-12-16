package com.georgiopoulos.core.design.widget.icon

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import com.georgiopoulos.core.design.DesignSystemStyle
import com.georgiopoulos.core.design.theme.DesignSystemTheme
import com.georgiopoulos.core.design.widget.icon.IconSize.NORMAL

@Composable
fun DesignSystemIconImpl(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    @DrawableRes iconId: Int,
    iconSize: IconSize = NORMAL,
    style: DesignSystemStyle = DesignSystemStyle.LIGHT,
    contentDescription: String? = null,
) {
    val widgetSize = iconSize.size
    val paddingSize = iconSize.padding
    Box(
        modifier = modifier
            .size(widgetSize),
        contentAlignment = Alignment.Center,
    ) {
        if (iconId != 0) {
            Image(
                painter = painterResource(id = iconId),
                contentDescription = contentDescription,
                modifier = Modifier
                    .size(widgetSize - (paddingSize * 2)),
                colorFilter = ColorFilter.tint(
                    when (style) {
                        DesignSystemStyle.DYNAMIC -> if (enabled) {
                            DesignSystemTheme.colors.semanticColors.iconTint
                        } else {
                            DesignSystemTheme.colors.semanticColors.iconTintDisabled
                        }

                        DesignSystemStyle.LIGHT -> if (enabled) {
                            DesignSystemTheme.colors.semanticColors.iconTintLight
                        } else {
                            DesignSystemTheme.colors.semanticColors.iconTintDisabled
                        }

                        DesignSystemStyle.DARK -> if (enabled) {
                            DesignSystemTheme.colors.semanticColors.iconTintDark
                        } else {
                            DesignSystemTheme.colors.semanticColors.iconTintDisabled
                        }
                    },
                ),
            )
        }
    }
}
