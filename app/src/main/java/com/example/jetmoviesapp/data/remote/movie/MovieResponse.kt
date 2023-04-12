package com.example.jetmoviesapp.data.remote.movie

import com.example.jetmoviesapp.data.remote.movie.Movie


data class MovieResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int,
)
