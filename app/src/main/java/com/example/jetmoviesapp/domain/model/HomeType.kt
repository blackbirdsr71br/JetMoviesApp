package com.example.jetmoviesapp.domain.model

import com.example.jetmoviesapp.data.remote.movie.Movie


sealed class HomeType {
    data class Popular(val popular: List<Movie>) : HomeType()
    data class TopRated(val topRated: List<Movie>) : HomeType()
}