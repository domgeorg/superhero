package com.georgiopoulos.core.database.di

import android.app.Application
import androidx.room.Room
import com.georgiopoulos.core.database.SuperHeroDatabase
import com.georgiopoulos.core.database.SuperHeroDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Provides a singleton instance of the {@link SuperHeroDatabase}.
     *
     * @param application The application context.
     * @return The Room database instance for superhero-related data.
     */
    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application,
    ): SuperHeroDatabase {
        return Room
            .databaseBuilder(application, SuperHeroDatabase::class.java, "SuperHero.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    /**
     * Provides a singleton instance of the {@link SuperHeroDao}.
     *
     * @param appDatabase The Room database instance for superhero-related data.
     * @return The data access object for superhero-related database operations.
     */
    @Provides
    @Singleton
    fun provideSuperHero(appDatabase: SuperHeroDatabase): SuperHeroDao {
        return appDatabase.superHeroDao()
    }
}
