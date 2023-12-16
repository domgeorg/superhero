package com.georgiopoulos.core.design

enum class DesignSystemStyle(
    private val id: Int,
) {
    DYNAMIC(0),
    LIGHT(1),
    DARK(2),
    ;

    companion object {
        fun resolve(id: Int): DesignSystemStyle = entries.find { it.id == id } ?: getDefault()

        fun getDefault(): DesignSystemStyle = DYNAMIC
    }
}
