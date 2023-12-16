package com.georgiopoulos.feature.home.component

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.georgiopoulos.core.design.DesignSystemStyle
import com.georgiopoulos.core.design.theme.DesignSystemTheme
import com.georgiopoulos.core.design.widget.button.DesignSystemButtonPrimary
import com.georgiopoulos.core.design.widget.text.DesignSystemText
import com.georgiopoulos.core.design.widget.text.TextType
import com.georgiopoulos.core_resources.R as Resources

@Composable
internal fun SuperHeroLoadingError(
    modifier: Modifier = Modifier,
    @StringRes messageResId: Int,
    onAction: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        Image(
            painterResource(id = Resources.drawable.superhero_logo),
            contentDescription = null,
            modifier = Modifier.size(200.dp),
        )

        DesignSystemText(
            modifier = Modifier
                .padding(DesignSystemTheme.spacings.spacing12)
                .align(Alignment.CenterHorizontally),
            text = stringResource(id = Resources.string.default_error_title),
            style = DesignSystemStyle.DARK,
            textType = TextType.TITLE_2,
        )

        DesignSystemText(
            modifier = Modifier
                .padding(DesignSystemTheme.spacings.spacing12)
                .align(Alignment.CenterHorizontally),
            text = stringResource(id = messageResId),
        )

        DesignSystemButtonPrimary(
            modifier = Modifier
                .padding(DesignSystemTheme.spacings.spacing12),
            text = stringResource(id = Resources.string.try_again),
        ) {
            onAction()
        }
    }
}
