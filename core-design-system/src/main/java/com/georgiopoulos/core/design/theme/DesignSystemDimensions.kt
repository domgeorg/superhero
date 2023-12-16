package com.georgiopoulos.core.design.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.georgiopoulos.core.design.theme.base.BaseDimensions

@Immutable
data class DesignSystemDimensions internal constructor(
    internal val buttonDimensions: ButtonDimensions,
    internal val loaderDimensions: LoaderDimensions,
    internal val navBarDimensions: NavBarDimensions,
    internal val iconDimensions: IconDimensions,
    internal val iconButtonDimensions: IconButtonDimensions,
) {

    internal companion object {
        @Composable
        fun build(): DesignSystemDimensions =
            DesignSystemDimensions(
                buttonDimensions = ButtonDimensions.build(),
                loaderDimensions = LoaderDimensions.build(),
                navBarDimensions = NavBarDimensions(),
                iconDimensions = IconDimensions.build(),
                iconButtonDimensions = IconButtonDimensions.build(),
            )
    }
}

@Immutable
internal data class ButtonDimensions(
    val margin: Dp,
    val heightNormal: Dp,
    val heightSmall: Dp,
    val minWidth: Dp,
    val secondaryButtonStrokeWidth: Dp,
) {
    internal companion object {
        @Composable
        fun build(): ButtonDimensions =
            ButtonDimensions(
                margin = BaseDimensions.buttonMargin,
                heightNormal = BaseDimensions.buttonHeightNormal,
                heightSmall = BaseDimensions.buttonHeightSmall,
                minWidth = BaseDimensions.buttonMinWidth,
                secondaryButtonStrokeWidth = BaseDimensions.buttonStrokeWidth,
            )
    }
}

@Immutable
internal data class LoaderDimensions(
    val circularSize: Dp,
) {
    internal companion object {
        @Composable
        fun build(): LoaderDimensions =
            LoaderDimensions(
                circularSize = BaseDimensions.loaderSize,
            )
    }
}

@Immutable
internal data class NavBarDimensions(
    val height: Dp = 56.dp,
    val navIconSize: Dp = 48.dp,
)

@Immutable
internal data class IconDimensions(
    val sizeNormal: Dp,
    val sizeSmall: Dp,
    val paddingNormal: Dp,
    val paddingSmall: Dp,
) {
    companion object {
        @Composable
        fun build(): IconDimensions =
            IconDimensions(
                sizeNormal = BaseDimensions.iconSizeNormal,
                sizeSmall = BaseDimensions.iconSizeSmall,
                paddingNormal = BaseDimensions.iconPaddingNormal,
                paddingSmall = BaseDimensions.iconPaddingSmall,
            )
    }
}

@Immutable
internal data class IconButtonDimensions(
    val size: Dp,
    val elevation: Dp,
) {
    companion object {
        @Composable
        fun build(): IconButtonDimensions =
            IconButtonDimensions(
                size = 48.dp,
                elevation = 8.dp,
            )
    }
}
