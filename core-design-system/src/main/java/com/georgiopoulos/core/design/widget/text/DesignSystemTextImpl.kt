package com.georgiopoulos.core.design.widget.text

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import com.georgiopoulos.core.design.DesignSystemStyle
import com.georgiopoulos.core.design.theme.DesignSystemTheme
import com.georgiopoulos.core.design.theme.Typography
import com.georgiopoulos.core.design.theme.base.BaseColors
import com.georgiopoulos.core.design.widget.text.MaxLinesConfig.ForcedMaxLines
import com.georgiopoulos.core.design.widget.text.MaxLinesConfig.UseTextStyleValue
import com.georgiopoulos.core.design.widget.text.TextType.BODY_1
import com.georgiopoulos.core.design.widget.text.TextType.BODY_1_HIGHLIGHT
import com.georgiopoulos.core.design.widget.text.TextType.BODY_2
import com.georgiopoulos.core.design.widget.text.TextType.BODY_2_HIGHLIGHT
import com.georgiopoulos.core.design.widget.text.TextType.CAPTION
import com.georgiopoulos.core.design.widget.text.TextType.CAPTION_DESTRUCTIVE
import com.georgiopoulos.core.design.widget.text.TextType.TITLE_1
import com.georgiopoulos.core.design.widget.text.TextType.TITLE_1_DESTRUCTIVE
import com.georgiopoulos.core.design.widget.text.TextType.TITLE_1_INFORMATIVE
import com.georgiopoulos.core.design.widget.text.TextType.TITLE_2
import com.georgiopoulos.core.design.widget.text.TextType.TITLE_2_DESTRUCTIVE
import com.georgiopoulos.core.design.widget.text.TextType.TITLE_2_INFORMATIVE
import com.georgiopoulos.core.design.widget.text.TextType.TITLE_3
import com.georgiopoulos.core.design.widget.text.TextType.TITLE_3_DESTRUCTIVE
import com.georgiopoulos.core.design.widget.text.TextType.TITLE_3_INFORMATIVE
import com.georgiopoulos.core.design.widget.text.TextType.TITLE_4
import com.georgiopoulos.core.design.widget.text.TextType.TITLE_4_DESTRUCTIVE
import com.georgiopoulos.core.design.widget.text.TextType.TITLE_4_INFORMATIVE
import timber.log.Timber

@Composable
fun DesignSystemTextImpl(
    text: String,
    modifier: Modifier = Modifier,
    alignment: TextAlign = TextAlign.Start,
    textType: TextType = BODY_1,
    links: List<Link> = emptyList(),
    overflow: TextOverflow = TextOverflow.Clip,
    style: DesignSystemStyle = DesignSystemStyle.LIGHT,
    maxLines: MaxLinesConfig = UseTextStyleValue,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    ProvideTextStyle(
        value = DesignSystemTheme.typography.run {
            getTextStyleFromTextType(textType)
        },
    ) {
        var buildClickable = if (
            textType != BODY_1 && textType != BODY_2 && links.isNotEmpty()
        ) {
            LaunchedEffect(textType, links) {
                Timber.d("You can only use links in \"Body1\" and \"Body2\" text types!")
            }
            false
        } else {
            true
        }

        buildClickable = buildClickable && if (links.any { !text.contains(it.text) }) {
            LaunchedEffect(links) {
                Timber.d("Invalid link config. Link parameters are missing from the text!")
            }
            false
        } else {
            true
        }

        if (buildClickable && links.isNotEmpty()) {
            TextWithLinks(
                text = text,
                textType = textType,
                links = links,
                alignment = alignment,
                style = style,
                maxLines = maxLines,
                overflow = overflow,
                modifier = modifier,
                onTextLayout = onTextLayout,
            )
        } else {
            TextWithoutLinks(
                text = text,
                textType = textType,
                alignment = alignment,
                style = style,
                maxLines = maxLines,
                overflow = overflow,
                modifier = modifier,
                onTextLayout = onTextLayout,
            )
        }
    }
}

@Composable
private fun Typography.getTextStyleFromTextType(textType: TextType): TextStyle =
    when (textType) {
        TITLE_1 -> this.text.title1
        TITLE_1_DESTRUCTIVE -> this.text.title1Destructive
        TITLE_1_INFORMATIVE -> this.text.title1Informative
        TITLE_2 -> this.text.title2
        TITLE_2_DESTRUCTIVE -> this.text.title2Destructive
        TITLE_2_INFORMATIVE -> this.text.title2Informative
        TITLE_3 -> this.text.title3
        TITLE_3_DESTRUCTIVE -> this.text.title3Destructive
        TITLE_3_INFORMATIVE -> this.text.title3Informative
        TITLE_4 -> this.text.title4
        TITLE_4_DESTRUCTIVE -> this.text.title4Destructive
        TITLE_4_INFORMATIVE -> this.text.title4Informative
        BODY_1 -> this.text.body1
        BODY_1_HIGHLIGHT -> this.text.body1Highlight
        BODY_2 -> this.text.body2
        BODY_2_HIGHLIGHT -> this.text.body2Highlight
        CAPTION -> this.text.caption
        CAPTION_DESTRUCTIVE -> this.text.captionDestructive
    }

@Composable
private fun TextWithoutLinks(
    text: String,
    textType: TextType,
    alignment: TextAlign,
    style: DesignSystemStyle,
    maxLines: MaxLinesConfig,
    overflow: TextOverflow,
    modifier: Modifier,
    onTextLayout: (TextLayoutResult) -> Unit,
) {
    Text(
        text = if (textType.isAllCaps) text.uppercase() else text,
        overflow = overflow,
        modifier = modifier,
        textAlign = alignment,
        maxLines = when (maxLines) {
            is ForcedMaxLines -> maxLines.maxLines
            UseTextStyleValue -> textType.maxLines
        },
        color = when (style) {
            DesignSystemStyle.LIGHT -> BaseColors.neutral100
            else -> LocalTextStyle.current.color
        },
        onTextLayout = onTextLayout,
    )
}

@Composable
private fun TextWithLinks(
    text: String,
    textType: TextType,
    links: List<Link>,
    alignment: TextAlign,
    style: DesignSystemStyle,
    maxLines: MaxLinesConfig,
    modifier: Modifier,
    overflow: TextOverflow,
    onTextLayout: (TextLayoutResult) -> Unit,
) {
    val spanStyle = SpanStyle(
        fontSize = LocalTextStyle.current.fontSize,
        fontWeight = LocalTextStyle.current.fontWeight,
        fontFamily = LocalTextStyle.current.fontFamily,
        fontStyle = LocalTextStyle.current.fontStyle,
        color = when (style) {
            DesignSystemStyle.LIGHT -> BaseColors.neutral100
            else -> LocalTextStyle.current.color
        },
    )
    val annotatedText = buildAnnotatedString {
        append(text)
        links.forEach {
            val linkParam = it.text
            val spanStart = text.indexOf(linkParam.toString())
            val spanEnd = spanStart + linkParam.length
            addStyle(
                style = spanStyle.copy(
                    color = when (style) {
                        DesignSystemStyle.LIGHT -> BaseColors.neutral100
                        else -> DesignSystemTheme.colors.text.link.normal.normal
                    },
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold,
                ),
                start = spanStart,
                end = spanEnd,
            )
            addStringAnnotation(
                tag = linkParam.toString(),
                annotation = linkParam.toString(),
                start = spanStart,
                end = spanEnd,
            )
        }
    }
    ClickableText(
        text = annotatedText,
        style = LocalTextStyle.current.copy(textAlign = alignment),
        modifier = modifier,
        maxLines = when (maxLines) {
            is ForcedMaxLines -> maxLines.maxLines
            UseTextStyleValue -> textType.maxLines
        },
        overflow = overflow,
        onTextLayout = onTextLayout,
    ) { offset ->
        links.forEach { link ->
            annotatedText.getStringAnnotations(
                tag = link.text.toString(),
                start = offset,
                end = offset,
            ).firstOrNull()?.let {
                link.onClick()
            }
        }
    }
}

sealed interface MaxLinesConfig {
    data object UseTextStyleValue : MaxLinesConfig

    data class ForcedMaxLines(
        val maxLines: Int,
    ) : MaxLinesConfig
}
