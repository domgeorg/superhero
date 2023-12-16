package com.georgiopoulos.core.design.theme

import androidx.annotation.FloatRange
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.georgiopoulos.core.design.theme.base.BaseColors

const val DEFAULT_RIPPLE_ALPHA: Float = 0.5f

@Composable
internal fun RippleTheme(
    color: Color = BaseColors.neutral300,
    @FloatRange(from = 0.0, to = 1.0) alpha: Float = DEFAULT_RIPPLE_ALPHA,
    content: @Composable () -> Unit,
) {
    val rippleTheme = object : RippleTheme {
        @Composable
        override fun defaultColor() = color

        @Composable
        override fun rippleAlpha(): RippleAlpha =
            RippleAlpha(
                draggedAlpha = alpha,
                focusedAlpha = alpha,
                hoveredAlpha = alpha,
                pressedAlpha = alpha,
            )
    }

    CompositionLocalProvider(LocalRippleTheme provides rippleTheme) {
        content()
    }
}

/**
 * Most of this documentation is copied from [clickable] and [rememberRipple].
 *
 * Configure any component to receive clicks via input or accessibility "click" event, while maintaining a consistent
 * API for displaying a ripple effect that matches the specifications of DesignSystem components.
 *
 * @param rippleColor The color of the ripple. This color will then have RippleTheme.rippleAlpha applied to
 * calculate the final color used to draw the ripple. If Color.Unspecified is provided the color used will be
 * RippleTheme.defaultColor instead.
 * @param rippleRadius The radius for the ripple. If [Dp.Unspecified] is provided then the size will be calculated based
 * on the target layout size
 * @param shape The shape the ripple effect will be clipped with; It should be the same as the shape of the component by
 * this modifier is used on by default
 * @param bounded If true, ripples are clipped by the bounds of the target layout. Unbounded ripples always animate from
 * the target layout center, bounded ripples animate from the touch position
 * @param interactionSource [MutableInteractionSource] that will be used to dispatch
 * [PressInteraction.Press] when this clickable is pressed. Only the initial (first) press will be
 * recorded and dispatched with [MutableInteractionSource].
 * @param enabled Controls the enabled state. When `false`, [onClick], and this modifier will
 * appear disabled for accessibility services
 * @param onClickLabel semantic / accessibility label for the [onClick] action
 * @param semanticRole the type of user interface element. Accessibility services might use this
 * to describe the element or do customizations
 * @param onClick will be called when user clicks on the component
 */
fun Modifier.rippleClickable(
    rippleColor: Color = Color.Unspecified,
    rippleRadius: Dp = Dp.Unspecified,
    shape: Shape = RoundedCornerShape(8.dp),
    enabled: Boolean = true,
    bounded: Boolean = true,
    interactionSource: MutableInteractionSource,
    semanticRole: Role = Role.Button,
    onClickLabel: String? = null,
    onClick: () -> Unit,
): Modifier =
    composed {

        this
            .clip(
                shape = shape,
            )
            .clickable(
                interactionSource = remember { interactionSource },
                indication = rememberRipple(
                    bounded = bounded,
                    radius = rippleRadius,
                    color = if (rippleColor.isSpecified) {
                        rippleColor
                    } else {
                        LocalRippleTheme.current.defaultColor()
                    },
                ),
                enabled = enabled,
                onClickLabel = onClickLabel,
                role = semanticRole,
                onClick = onClick,
            )
    }
