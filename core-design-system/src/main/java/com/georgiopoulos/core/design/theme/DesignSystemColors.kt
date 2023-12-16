package com.georgiopoulos.core.design.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.georgiopoulos.core.design.theme.base.BaseColors
import com.georgiopoulos.core.design.theme.base.TextColors
import com.georgiopoulos.core.design.theme.base.TextModifierColors
import com.georgiopoulos.core.design.theme.base.TextStateColors

@Immutable
data class DesignSystemColors(
    val primaryColors: PrimaryColors,
    val secondaryColors: SecondaryColors,
    val functionalColors: FunctionalColors,
    val neutralColors: NeutralColors,
    val semanticColors: SemanticColors,
    val primaryButton: ButtonStateColors,
    val iconButton: IconButtonStateColors,
    val text: TextColors,
) {
    internal companion object {
        @Composable
        fun build(dark: Boolean): DesignSystemColors {
            val primaryColors = PrimaryColors()
            val secondaryColors = SecondaryColors()
            val functionalColors = FunctionalColors()
            val neutralColors = NeutralColors()
            val semanticColors = SemanticColors.build(dark)

            return if (dark) {
                buildDarkColors(
                    primaryColors = primaryColors,
                    secondaryColors = secondaryColors,
                    functionalColors = functionalColors,
                    neutralColors = neutralColors,
                    semanticColors = semanticColors,
                )
            } else {
                buildLightColors(
                    primaryColors = primaryColors,
                    secondaryColors = secondaryColors,
                    functionalColors = functionalColors,
                    neutralColors = neutralColors,
                    semanticColors = semanticColors,
                )
            }
        }

        @Composable
        private fun buildLightColors(
            primaryColors: PrimaryColors,
            secondaryColors: SecondaryColors,
            functionalColors: FunctionalColors,
            neutralColors: NeutralColors,
            semanticColors: SemanticColors,
        ): DesignSystemColors =
            DesignSystemColors(
                primaryColors = primaryColors,
                secondaryColors = secondaryColors,
                functionalColors = functionalColors,
                neutralColors = neutralColors,
                semanticColors = semanticColors,
                primaryButton = ButtonStateColors(
                    default = ButtonColors(
                        backgroundColor = primaryColors.purple,
                        disabledBackgroundColor = semanticColors.primaryPurple40,
                        textColor = BaseColors.neutral100,
                        disabledTextColor = semanticColors.white40,
                        loadingProgressColor = BaseColors.tealGreen400,
                        rippleColor = BaseColors.neutral100,
                    ),
                    destructive = ButtonColors(
                        backgroundColor = functionalColors.destructiveRed,
                        disabledBackgroundColor = semanticColors.destructiveRed40,
                        textColor = BaseColors.neutral100,
                        disabledTextColor = semanticColors.white40,
                        loadingProgressColor = BaseColors.neutral100,
                        rippleColor = BaseColors.neutral100,
                    ),
                ),
                iconButton = IconButtonStateColors(
                    normal = IconButtonStateColors.IconButtonColors(
                        backgroundColor = neutralColors.neutral0,
                        disabledBackgroundColor = neutralColors.neutral0,
                        rippleColor = neutralColors.neutral2,
                    ),
                ),
                text = buildLightTextColors(),
            )

        @Composable
        private fun buildLightTextColors() = TextColors(
            title = TextStateColors(
                normal = TextModifierColors(
                    normal = BaseColors.navy400,
                    disabled = BaseColors.navy100,
                ),
                negative = TextModifierColors(
                    normal = BaseColors.red400,
                    disabled = BaseColors.red200,
                ),
                highlight = TextModifierColors(
                    normal = BaseColors.blue400,
                    disabled = BaseColors.blue200,
                ),
            ),
            body = TextStateColors(
                normal = TextModifierColors(
                    normal = BaseColors.neutral500,
                ),
            ),
            caption = TextStateColors(
                normal = TextModifierColors(
                    normal = BaseColors.neutral500,
                    disabled = BaseColors.navy100,
                ),
                negative = TextModifierColors(
                    normal = BaseColors.red400,
                    disabled = BaseColors.red200,
                ),
            ),
            link = TextStateColors(
                normal = TextModifierColors(
                    normal = BaseColors.blue400,
                    disabled = BaseColors.blue200,
                ),
            ),
        )

        @Composable
        private fun buildDarkColors(
            primaryColors: PrimaryColors,
            secondaryColors: SecondaryColors,
            functionalColors: FunctionalColors,
            neutralColors: NeutralColors,
            semanticColors: SemanticColors,
        ): DesignSystemColors =
            buildLightColors(
                primaryColors = primaryColors,
                secondaryColors = secondaryColors,
                functionalColors = functionalColors,
                neutralColors = neutralColors,
                semanticColors = semanticColors,
            ).copy(
                text = buildDarkTextColors(),
            )

        @Composable
        private fun buildDarkTextColors() = TextColors(
            title = TextStateColors(
                normal = TextModifierColors(
                    normal = BaseColors.navy500,
                    disabled = BaseColors.navy100,
                ),
                negative = TextModifierColors(
                    normal = BaseColors.red400,
                    disabled = BaseColors.red200,
                ),
                highlight = TextModifierColors(
                    normal = BaseColors.blue400,
                    disabled = BaseColors.blue200,
                ),
            ),
            body = TextStateColors(
                normal = TextModifierColors(
                    normal = BaseColors.neutral500,
                ),
            ),
            caption = TextStateColors(
                normal = TextModifierColors(
                    normal = BaseColors.neutral500,
                    disabled = BaseColors.navy100,
                ),
                negative = TextModifierColors(
                    normal = BaseColors.red400,
                    disabled = BaseColors.red200,
                ),
            ),
            link = TextStateColors(
                normal = TextModifierColors(
                    normal = BaseColors.blue400,
                    disabled = BaseColors.blue200,
                ),
            ),
        )
    }
}

@Immutable
data class ButtonColors(
    val backgroundColor: Color = Color.Transparent,
    val borderStrokeColor: Color = Color.Transparent,
    val disabledBackgroundColor: Color = Color.Transparent,
    val disabledBorderStrokeColor: Color = Color.Transparent,
    val rippleColor: Color,
    val textColor: Color,
    val disabledTextColor: Color,
    val loadingProgressColor: Color = Color.Transparent,
)

@Immutable
data class ButtonStateColors(
    val default: ButtonColors,
    val destructive: ButtonColors,
) {
    fun getTextColorForState(
        destructive: Boolean,
        enabled: Boolean,
    ): Color =
        if (destructive) {
            this.destructive
        } else {
            default
        }.run {
            if (enabled) {
                textColor
            } else {
                disabledTextColor
            }
        }
}

@Immutable
data class IconButtonStateColors(
    val normal: IconButtonColors,
) {

    @Immutable
    data class IconButtonColors(
        val backgroundColor: Color = Color.Transparent,
        val disabledBackgroundColor: Color = Color.Transparent,
        val rippleColor: Color,
    )
}
