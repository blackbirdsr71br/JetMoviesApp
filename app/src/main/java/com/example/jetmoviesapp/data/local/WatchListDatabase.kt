package com.example.jetmoviesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetmoviesapp.data.local.entities.GenresEntity
import com.example.jetmoviesapp.data.local.entities.MovieEntity

@Database(
    entities = [
        MovieEntity::class,
        GenresEntity::class,
    ],
    version = 4,
)
abstract class WatchListDatabase : RoomDatabase() {

    abstract fun watchListDao(): WatchListDao
    abstract fun genresListDao(): GenresListDao
}
