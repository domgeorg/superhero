package com.georgiopoulos.core.design.widget.icon

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.georgiopoulos.core.design.DesignSystemStyle
import com.georgiopoulos.core.design.widget.icon.IconSize.NORMAL
import com.georgiopoulos.core.design.widget.icon.IconType.NONE

@Composable
fun DesignSystemIcon(
    modifier: Modifier = Modifier,
    iconType: IconType = NONE,
    iconSize: IconSize = NORMAL,
    style: DesignSystemStyle = DesignSystemStyle.LIGHT,
) {
    DesignSystemIconImpl(
        modifier = modifier,
        iconId = iconType.resId,
        iconSize = iconSize,
        style = style,
    )
}
