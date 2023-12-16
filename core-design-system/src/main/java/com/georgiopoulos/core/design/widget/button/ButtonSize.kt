package com.georgiopoulos.core.design.widget.button

import androidx.compose.ui.unit.Dp
import com.georgiopoulos.core.design.theme.base.BaseDimensions

enum class ButtonSize(
    private val id: Int,
    private val size: Dp,
) {
    NORMAL(0, BaseDimensions.buttonHeightNormal),
    SMALL(1, BaseDimensions.buttonHeightSmall),
    ;

    companion object {
        fun resolve(id: Int): ButtonSize = entries.find { it.id == id } ?: NORMAL
    }
}