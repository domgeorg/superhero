package com.georgiopoulos.core.design.widget.text

import androidx.compose.ui.graphics.Color
import com.georgiopoulos.core.design.theme.base.BaseColors
import com.georgiopoulos.core.design.widget.text.TextSize.LINE_HEIGHT_16
import com.georgiopoulos.core.design.widget.text.TextSize.LINE_HEIGHT_18
import com.georgiopoulos.core.design.widget.text.TextSize.LINE_HEIGHT_20
import com.georgiopoulos.core.design.widget.text.TextSize.LINE_HEIGHT_26
import com.georgiopoulos.core.design.widget.text.TextSize.LINE_HEIGHT_40
import com.georgiopoulos.core.design.widget.text.TextSize.SIZE_TITLE_1
import com.georgiopoulos.core.design.widget.text.TextSize.SIZE_TITLE_2
import com.georgiopoulos.core.design.widget.text.TextSize.SIZE_TITLE_3
import com.georgiopoulos.core.design.widget.text.TextSize.SIZE_TITLE_4
import com.georgiopoulos.core.design.widget.text.TextSize.TEXT_SIZE_12
import com.georgiopoulos.core.design.widget.text.TextSize.TEXT_SIZE_14
import com.georgiopoulos.core.design.widget.text.TextSize.TEXT_SIZE_16

enum class TextType(
    private val id: Int,
    val textColor: Color,
    val textSizeSp: Float,
    val lineHeightSp: Float,
    val isBold: Boolean = true,
    val maxLines: Int = Int.MAX_VALUE,
    val isAllCaps: Boolean = false,
) {
    TITLE_1(0, BaseColors.navy400, SIZE_TITLE_1, LINE_HEIGHT_40, isBold = false),
    TITLE_2(1, BaseColors.navy400, SIZE_TITLE_2, LINE_HEIGHT_26, isBold = false),
    TITLE_3(2, BaseColors.navy400, SIZE_TITLE_3, LINE_HEIGHT_20, isBold = false),
    TITLE_4(3, BaseColors.navy400, SIZE_TITLE_4, LINE_HEIGHT_18, isBold = false),

    TITLE_1_DESTRUCTIVE(4, BaseColors.red400, SIZE_TITLE_1, LINE_HEIGHT_40),
    TITLE_2_DESTRUCTIVE(5, BaseColors.red400, SIZE_TITLE_2, LINE_HEIGHT_26),
    TITLE_3_DESTRUCTIVE(6, BaseColors.red400, SIZE_TITLE_3, LINE_HEIGHT_20),
    TITLE_4_DESTRUCTIVE(7, BaseColors.red400, SIZE_TITLE_4, LINE_HEIGHT_18),

    TITLE_1_INFORMATIVE(8, BaseColors.blue400, SIZE_TITLE_1, LINE_HEIGHT_40),
    TITLE_2_INFORMATIVE(9, BaseColors.blue400, SIZE_TITLE_2, LINE_HEIGHT_26),
    TITLE_3_INFORMATIVE(10, BaseColors.blue400, SIZE_TITLE_3, LINE_HEIGHT_20),
    TITLE_4_INFORMATIVE(11, BaseColors.blue400, SIZE_TITLE_4, LINE_HEIGHT_18),

    BODY_1(12, BaseColors.navy400, TEXT_SIZE_16, LINE_HEIGHT_20, isBold = false),
    BODY_1_HIGHLIGHT(13, BaseColors.navy500, TEXT_SIZE_16, LINE_HEIGHT_20, isBold = false),

    BODY_2(14, BaseColors.navy400, TEXT_SIZE_14, LINE_HEIGHT_18, isBold = false),
    BODY_2_HIGHLIGHT(15, BaseColors.navy500, TEXT_SIZE_14, LINE_HEIGHT_18, isBold = false),

    CAPTION(16, BaseColors.neutral500, TEXT_SIZE_12, LINE_HEIGHT_16, isBold = false),
    CAPTION_DESTRUCTIVE(
        id = 17,
        textColor = BaseColors.red400,
        textSizeSp = TEXT_SIZE_12,
        lineHeightSp = LINE_HEIGHT_16,
        isBold = false,
    ),
    ;

    companion object {
        fun resolve(id: Int): TextType = entries.find { it.id == id } ?: BODY_1
    }
}
