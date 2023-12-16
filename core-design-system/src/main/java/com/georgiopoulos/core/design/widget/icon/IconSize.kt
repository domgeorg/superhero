package com.georgiopoulos.core.design.widget.icon

import androidx.compose.ui.unit.Dp
import com.georgiopoulos.core.design.theme.base.BaseDimensions

enum class IconSize(
    private val id: Int,
    val size: Dp,
    val padding: Dp,
) {
    NORMAL(0, BaseDimensions.iconSizeNormal, BaseDimensions.iconPaddingNormal),
    SMALL(1, BaseDimensions.iconSizeSmall, BaseDimensions.iconPaddingSmall),
    BIG(1, BaseDimensions.iconSizeBig, BaseDimensions.iconPaddingBig),
    ;

    companion object {
        fun resolve(id: Int): IconSize = entries.find { it.id == id } ?: NORMAL
    }
}
