package com.example.jetmoviesapp.data.local.entities

import androidx.room.Entity

@Entity(primaryKeys = ["playlistId", "songId"])
data class GenresMovies(
    val idGenre: Int,
    val idMovie: Int,
)
