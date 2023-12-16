package com.georgiopoulos.feature.details.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.georgiopoulos.core.design.DesignSystemStyle
import com.georgiopoulos.core.design.theme.DesignSystemTheme
import com.georgiopoulos.core.design.widget.button.ButtonSize
import com.georgiopoulos.core.design.widget.button.DesignSystemButtonPrimary
import com.georgiopoulos.core.design.widget.icon.DesignSystemIcon
import com.georgiopoulos.core.design.widget.icon.IconSize
import com.georgiopoulos.core.design.widget.icon.IconType
import com.georgiopoulos.core.design.widget.text.DesignSystemText
import com.georgiopoulos.core_resources.R as Resources

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AreYouSureDialog(
    onDismiss: () -> Unit,
    onRemove: () -> Unit,
) {
    BasicAlertDialog(
        onDismissRequest = {
            onDismiss()
        },
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(DesignSystemTheme.spacings.spacing8))
                .background(DesignSystemTheme.colors.neutralColors.neutral0)
                .padding(DesignSystemTheme.spacings.spacing16),
            contentAlignment = Alignment.Center,
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                DesignSystemIcon(
                    modifier = Modifier.padding(DesignSystemTheme.spacings.spacing8),
                    iconSize = IconSize.BIG,
                    style = DesignSystemStyle.DARK,
                    iconType = IconType.HELP,
                )

                DesignSystemText(
                    modifier = Modifier.padding(DesignSystemTheme.spacings.spacing8),
                    text = stringResource(id = Resources.string.dialog_remove_from_squad_message),
                )

                DesignSystemButtonPrimary(
                    modifier = Modifier
                        .padding(end = DesignSystemTheme.spacings.spacing8),
                    buttonSize = ButtonSize.SMALL,
                    destructive = true,
                    text = stringResource(id = Resources.string.dialog_remove_from_squad_button)
                ) {
                    onDismiss()
                    onRemove()
                }
            }
        }
    }
}
