package com.example.remote.data.model

import com.example.remote.data.remote.movie.Movie

sealed class HomeType {
    data class Popular(val popular: List<Movie>) : HomeType()
    data class TopRated(val topRated: List<Movie>) : HomeType()
}
