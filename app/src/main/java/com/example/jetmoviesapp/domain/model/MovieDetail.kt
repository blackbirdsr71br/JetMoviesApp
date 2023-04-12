package com.example.jetmoviesapp.domain.model

import com.example.jetmoviesapp.data.remote.genre.Genre
import com.example.jetmoviesapp.data.remote.movie.MovieResponse
import com.example.jetmoviesapp.data.remote.movie_detail.Credit
import com.example.jetmoviesapp.data.remote.movie_detail.Language

data class MovieDetail(
    val id: Int,
    val backdropPath: String?,
    val genres: List<Genre>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String?,
    val posterPath: String?,
    val releaseDate: String,
    val runtime: Int?,
    val spokenLanguages: List<Language>,
    val status: String,
    val tagline: String?,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    val similar: MovieResponse,
    val credit: Credit
)
