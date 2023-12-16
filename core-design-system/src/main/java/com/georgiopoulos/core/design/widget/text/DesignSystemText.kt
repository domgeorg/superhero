package com.georgiopoulos.core.design.widget.text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.georgiopoulos.core.design.DesignSystemStyle
import com.georgiopoulos.core.design.widget.text.TextType.BODY_1

@Composable
fun DesignSystemText(
    modifier: Modifier = Modifier,
    text: String,
    alignment: TextAlign = TextAlign.Start,
    textType: TextType = BODY_1,
    overflow: TextOverflow = TextOverflow.Clip,
    style: DesignSystemStyle = DesignSystemStyle.DARK,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    maxLines: MaxLinesConfig = MaxLinesConfig.UseTextStyleValue,
) {
    DesignSystemTextImpl(
        text = text,
        modifier = modifier,
        alignment = alignment,
        textType = textType,
        overflow = overflow,
        style = style,
        onTextLayout = onTextLayout,
        maxLines = maxLines,
    )
}