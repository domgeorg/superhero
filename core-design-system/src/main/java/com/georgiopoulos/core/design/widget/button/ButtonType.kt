package com.georgiopoulos.core.design.widget.button

import androidx.compose.ui.unit.TextUnit
import com.georgiopoulos.core.design.theme.base.BaseDimensions

enum class ButtonType(
    private val id: Int,
    val textSize: TextUnit,
) {
    PRIMARY(0, BaseDimensions.buttonTextSize),
    SECONDARY(1, BaseDimensions.buttonTextSize),
    ;

    companion object {
        fun resolve(id: Int): ButtonType = entries.find { it.id == id } ?: PRIMARY
    }
}
