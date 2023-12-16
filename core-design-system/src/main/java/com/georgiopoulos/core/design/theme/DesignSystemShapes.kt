package com.georgiopoulos.core.design.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Shape
import com.georgiopoulos.core.design.theme.base.BaseDimensions

@Immutable
data class DesignSystemShapes(
    val primaryButton: Shape,
    val secondaryButton: Shape,
    val inputField: Shape,
    val iconButton: Shape,
) {
    internal companion object {
        @Composable
        internal fun build(): DesignSystemShapes =
            DesignSystemShapes(
                primaryButton = RoundedCornerShape(
                    size = BaseDimensions.buttonCornerRadius,
                ),
                secondaryButton = RoundedCornerShape(
                    size = BaseDimensions.buttonCornerRadius,
                ),
                inputField = RoundedCornerShape(
                    size = BaseDimensions.inputFieldCornerRadius
                ),
                iconButton = RoundedCornerShape(
                    size = BaseDimensions.buttonCornerRadius,
                ),
            )
    }
}
