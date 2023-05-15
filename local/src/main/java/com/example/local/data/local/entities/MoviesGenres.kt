package com.example.local.data.local.entities

import androidx.room.Entity

@Entity(primaryKeys = ["idGenre", "idMovie"])
data class MoviesGenres(
    val idGenre: Int,
    val idMovie: Int
)
