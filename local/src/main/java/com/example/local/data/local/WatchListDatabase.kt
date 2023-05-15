package com.example.local.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.local.data.local.entities.GenresEntity
import com.example.local.data.local.entities.MovieEntity
import com.example.local.data.local.entities.MoviesGenres

@Database(
    entities = [
        MovieEntity::class,
        GenresEntity::class,
        MoviesGenres::class
    ],
    version = 5
)
abstract class WatchListDatabase : RoomDatabase() {

    abstract fun watchListDao(): WatchListDao
    abstract fun genresListDao(): GenresListDao
}
