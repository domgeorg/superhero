package com.georgiopoulos.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.georgiopoulos.core.database.entity.SuperHeroEntity

@Database(
    entities = [SuperHeroEntity::class],
    version = 1,
    exportSchema = true,
)
abstract class SuperHeroDatabase : RoomDatabase() {

    abstract fun superHeroDao(): SuperHeroDao
}
