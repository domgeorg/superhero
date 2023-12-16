package com.georgiopoulos.core.design.theme

import androidx.annotation.FloatRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.georgiopoulos.core.design.theme.base.BaseColors

@Immutable
data class PrimaryColors internal constructor(
    val green: Color = BaseColors.green400,
    val blue: Color = BaseColors.navy400,
    val purple: Color = BaseColors.purple400,
    val orange: Color = BaseColors.orange400,
    val informativeBlue: Color = BaseColors.blue400,
    val highlightBlue: Color = BaseColors.blue300,
    val highlightOrange: Color = BaseColors.orange300,
)

@Immutable
data class SecondaryColors internal constructor(
    val lightGreen: Color = BaseColors.tealGreen100,
    val lightBlue: Color = BaseColors.blue200,
    val lightOrange: Color = BaseColors.orange200,
    val lightPurple: Color = BaseColors.purple200,
    val backgroundGreen: Color = BaseColors.green100,
    val backgroundBlue: Color = BaseColors.blue100,
    val backgroundOrange: Color = BaseColors.orange100,
)

@Immutable
data class FunctionalColors internal constructor(
    val green: Color = BaseColors.green400,
    val blue: Color = BaseColors.navy400,
    val destructiveRed: Color = BaseColors.red400,
    val orange: Color = BaseColors.orange400,
    val warningYellow: Color = BaseColors.yellow400,
    val informativeBlue: Color = BaseColors.blue400,
    val purple: Color = BaseColors.purple400,
)

@Immutable
data class NeutralColors internal constructor(
    val neutral0: Color = BaseColors.neutral100,
    val neutral1: Color = BaseColors.neutral200,
    val neutral2: Color = BaseColors.neutral300,
    val neutral3: Color = BaseColors.neutral400,
    val neutral4: Color = BaseColors.neutral500,
    val neutral5: Color = BaseColors.neutral600,
    val neutral6: Color = BaseColors.neutral700,
)

@Immutable
data class SemanticColors internal constructor(
    val backgroundPrimary: Color = BaseColors.neutral100,
    internal val white40: Color = BaseColors.neutral100.withAlpha(alpha40),
    internal val destructiveRed40: Color = BaseColors.red400.withAlpha(alpha40),
    internal val primaryPurple40: Color = BaseColors.purple400.withAlpha(alpha40),
    internal val primaryInformativeBlue40: Color = BaseColors.blue400.withAlpha(alpha40),
    internal val textNumber: Color,
    internal val textTitle: Color,
    internal val textTitleDestructive: Color,
    internal val textTitleInformative: Color,
    internal val textBody: Color,
    internal val textCaption: Color,
    internal val iconTint: Color,
    internal val iconTintDisabled: Color,
    internal val iconTintLight: Color,
    internal val iconTintDark: Color,
    internal val functionalDestructiveRed10: Color = BaseColors.red400.withAlpha(alpha10),
) {
    internal companion object {
        @Composable
        fun build(dark: Boolean): SemanticColors =
            if (!dark) {
                SemanticColors(
                    // Text
                    textNumber = BaseColors.navy400,
                    textTitle = BaseColors.navy400,
                    textTitleDestructive = BaseColors.red400,
                    textTitleInformative = BaseColors.blue400,
                    textBody = BaseColors.neutral500,
                    textCaption = BaseColors.neutral500,
                    // Icon
                    iconTint = BaseColors.navy400,
                    iconTintDisabled = BaseColors.neutral400,
                    iconTintLight = BaseColors.neutral100,
                    iconTintDark = BaseColors.navy400,
                    // InputField
                    functionalDestructiveRed10 = BaseColors.red300,
                )
            } else {
                SemanticColors(
                    backgroundPrimary = BaseColors.neutral500,
                    // Text
                    textNumber = BaseColors.navy400,
                    textTitle = BaseColors.navy400,
                    textTitleDestructive = BaseColors.red400,
                    textTitleInformative = BaseColors.blue400,
                    textBody = BaseColors.neutral500,
                    textCaption = BaseColors.neutral500,
                    // Icon
                    iconTint = BaseColors.neutral100,
                    iconTintDisabled = BaseColors.neutral400,
                    iconTintLight = BaseColors.neutral100,
                    iconTintDark = BaseColors.navy400,
                    // InputField
                    functionalDestructiveRed10 = BaseColors.red300,
                )
            }
    }
}

internal const val alpha10: Float = 0.1f
internal const val alpha40: Float = 0.4f

fun Color.withAlpha(
    @FloatRange(from = 0.0, to = 1.0) alpha: Float,
): Color = this.copy(alpha = alpha)
