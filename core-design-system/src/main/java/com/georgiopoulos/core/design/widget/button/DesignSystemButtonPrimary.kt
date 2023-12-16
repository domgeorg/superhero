package com.georgiopoulos.core.design.widget.button

import android.view.HapticFeedbackConstants
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextOverflow
import com.georgiopoulos.core.design.theme.DesignSystemTheme
import com.georgiopoulos.core.design.theme.RippleTheme
import com.georgiopoulos.core.design.widget.button.ButtonSize.NORMAL
import com.georgiopoulos.core.design.widget.button.ButtonSize.SMALL
import com.georgiopoulos.core.design.widget.button.ButtonType.PRIMARY

@Composable
fun DesignSystemButtonPrimary(
    text: String,
    modifier: Modifier = Modifier,
    buttonSize: ButtonSize = NORMAL,
    destructive: Boolean = false,
    enabled: Boolean = true,
    hapticFeedbackEnabled: Boolean = false,
    onClick: () -> Unit,
) {
    val view = LocalView.current

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        RippleTheme(
            color = if (destructive) {
                DesignSystemTheme.colors.primaryButton.destructive.rippleColor
            } else {
                DesignSystemTheme.colors.primaryButton.default.rippleColor
            },
            alpha = 0.2f,
        ) {
            Button(
                colors = resolveButtonColors(destructive),
                enabled = enabled,
                elevation = null,
                shape = DesignSystemTheme.shapes.primaryButton,
                modifier = when (buttonSize) {
                    SMALL -> {
                        Modifier
                            .wrapContentWidth()
                            .defaultMinSize(
                                minHeight = DesignSystemTheme.dimensions.buttonDimensions.heightSmall,
                            )
                    }

                    NORMAL -> {
                        Modifier
                            .fillMaxWidth()
                            .defaultMinSize(
                                minHeight = DesignSystemTheme.dimensions.buttonDimensions.heightNormal,
                            )
                    }
                },
                onClick = {
                    if (hapticFeedbackEnabled) {
                        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                    }
                    onClick()
                },
                contentPadding = PaddingValues(
                    vertical = DesignSystemTheme.spacings.spacing4,
                    horizontal = DesignSystemTheme.spacings.spacing16,
                ),
            ) {
                Text(
                    text = text,
                    style = DesignSystemTheme.typography.button.primary,
                    fontSize = PRIMARY.textSize,
                    maxLines = 1,
                    color = DesignSystemTheme.colors.primaryButton.getTextColorForState(
                        destructive = destructive,
                        enabled = enabled,
                    ),
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
private fun resolveButtonColors(destructive: Boolean) =
    if (destructive) {
        ButtonDefaults.buttonColors(
            containerColor = DesignSystemTheme.colors.primaryButton.destructive.backgroundColor,
            disabledContainerColor = DesignSystemTheme.colors.primaryButton.destructive.disabledBackgroundColor,
            contentColor = DesignSystemTheme.colors.primaryButton.destructive.textColor,
            disabledContentColor = DesignSystemTheme.colors.primaryButton.destructive.disabledTextColor,
        )
    } else {
        ButtonDefaults.buttonColors(
            containerColor = DesignSystemTheme.colors.primaryButton.default.backgroundColor,
            disabledContainerColor = DesignSystemTheme.colors.primaryButton.default.disabledBackgroundColor,
            contentColor = DesignSystemTheme.colors.primaryButton.default.textColor,
            disabledContentColor = DesignSystemTheme.colors.primaryButton.default.disabledTextColor,
        )
    }
