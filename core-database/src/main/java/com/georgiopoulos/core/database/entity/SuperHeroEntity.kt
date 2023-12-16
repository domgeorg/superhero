package com.georgiopoulos.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SuperHeroEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val standardImageUrl: String,
    val description: String,
)
