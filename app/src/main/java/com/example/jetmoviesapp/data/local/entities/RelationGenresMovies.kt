package com.example.jetmoviesapp.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity(primaryKeys = ["playlistId", "songId"])
data class GenresMovies(
    val idGenre: Int,
    val idMovie: Int
)

data class BookWiMoviesGenresRelation(
    @Embedded
    var movies: MovieEntity,

    @Relation(
        parentColumn = "movieId",
        entity = MovieEntity::class,
        entityColumn = "Id",
        associateBy = Junction(
            value = GenresEntity::class,
            parentColumn = "movieId",
            entityColumn = "Id"
        )
    )
    var genresMovies: List<MovieEntity>
)
