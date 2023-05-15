package com.example.remote.domain.model

import com.example.remote.data.remote.movie.Movie

sealed class HomeType {
    data class Popular(val popular: List<com.example.remote.data.remote.movie.Movie>) : HomeType()
    data class TopRated(val topRated: List<com.example.remote.data.remote.movie.Movie>) : HomeType()
}
