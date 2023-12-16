package com.georgiopoulos.core.design.widget.icon

import com.georgiopoulos.core_resources.R as Resources

enum class IconType(
    private val id: Int,
    val resId: Int,
) {
    NONE(-1, 0),
    ARROW_BACK(0, Resources.drawable.icon_arrow_back),
    EXIT(1, Resources.drawable.icon_exit),
    HELP(2, Resources.drawable.icon_help),
    INFO(3, Resources.drawable.icon_info),
    RELOAD(4, Resources.drawable.icon_reload),
    ;

    companion object {
        fun resolve(id: Int): IconType = entries.find { it.id == id } ?: NONE
    }
}
