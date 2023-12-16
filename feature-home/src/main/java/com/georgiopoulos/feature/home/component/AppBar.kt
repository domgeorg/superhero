package com.georgiopoulos.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.georgiopoulos.core.design.DesignSystemStyle
import com.georgiopoulos.core.design.theme.DesignSystemTheme
import com.georgiopoulos.core.design.widget.text.DesignSystemText
import com.georgiopoulos.core.design.widget.text.TextType
import com.georgiopoulos.core_resources.R

@Composable
fun AppBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(DesignSystemTheme.colors.primaryColors.purple),
    ) {

        DesignSystemText(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(DesignSystemTheme.spacings.spacing16)
            ,
            text = stringResource(id = R.string.home_screen_title),
            textType = TextType.TITLE_2,
            style = DesignSystemStyle.LIGHT,
        )
    }
}