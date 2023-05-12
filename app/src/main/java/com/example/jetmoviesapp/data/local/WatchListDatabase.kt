package com.example.jetmoviesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetmoviesapp.data.local.entities.GenresEntity
import com.example.jetmoviesapp.data.local.entities.MovieEntity
import com.example.jetmoviesapp.data.local.entities.MoviesGenres

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
