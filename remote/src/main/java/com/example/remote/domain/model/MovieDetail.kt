package com.example.remote.domain.model

import com.example.remote.remote.genre.Genre
import com.example.remote.remote.movie.MovieResponse
import com.example.remote.remote.moviedetail.Credit
import com.example.remote.remote.moviedetail.Language

data class MovieDetail(
    val id: Int,
    val backdropPath: String?,
    val genres: List<com.example.remote.remote.genre.Genre>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String?,
    val posterPath: String?,
    val releaseDate: String,
    val runtime: Int?,
    val spokenLanguages: List<com.example.remote.remote.moviedetail.Language>,
    val status: String,
    val tagline: String?,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    val similar: com.example.remote.remote.movie.MovieResponse,
    val credit: com.example.remote.remote.moviedetail.Credit
)
