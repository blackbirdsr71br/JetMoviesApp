package com.example.jetmoviesapp.di

import android.content.Context
import androidx.room.Room
import com.example.jetmoviesapp.data.local.GenresListDao
import com.example.jetmoviesapp.data.local.WatchListDao
import com.example.jetmoviesapp.data.local.WatchListDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): WatchListDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            WatchListDatabase::class.java,
            "jetmovies_db",
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideWatchListDao(
        db: WatchListDatabase,
    ): WatchListDao {
        return db.watchListDao()
    }

    @Singleton
    @Provides
    fun provideGenresListDao(
        db: WatchListDatabase,
    ): GenresListDao {
        return db.genresListDao()
    }
}
