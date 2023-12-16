package com.georgiopoulos.core.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.georgiopoulos.core.database.entity.SuperHeroEntity

/**
 * Data access object (DAO) interface for superhero-related database operations.
 * This includes methods for inserting, deleting, and querying superhero entities.
 */
@Dao
interface SuperHeroDao {

    /**
     * Inserts a superhero entity into the database. If the entity already exists, it is replaced.
     *
     * @param entity The superhero entity to be inserted or replaced.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSuperHero(entity: SuperHeroEntity)

    /**
     * Removes a superhero entity from the database.
     *
     * @param entity The superhero entity to be removed.
     */
    @Delete
    suspend fun removeSuperHeron(entity: SuperHeroEntity)

    /**
     * Retrieves a list of superhero entities from the database with specified limits and offsets.
     *
     * @param limit  The maximum number of entities to retrieve.
     * @param offset The offset from the beginning of the result set.
     * @return The list of superhero entities.
     */
    @Query("SELECT * FROM SuperHeroEntity LIMIT :limit OFFSET :offset")
    suspend fun getSuperHeroList(limit: Int, offset: Int): List<SuperHeroEntity>

    /**
     * Checks if a superhero with a given name exists in the database.
     *
     * @param name The name of the superhero to check.
     * @return True if the superhero exists; false otherwise.
     */
    @Query("SELECT EXISTS(SELECT 1 FROM SuperHeroEntity WHERE name = :name LIMIT 1)")
    suspend fun isSuperHeroInDatabase(name: String): Boolean
}
