package com.example.jetmoviesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetmoviesapp.data.local.WatchListDao
import com.example.jetmoviesapp.data.local.entities.MovieEntity


@Database(entities = [MovieEntity::class], version = 2)
abstract class WatchListDatabase : RoomDatabase() {

    abstract fun watchListDao(): WatchListDao
}