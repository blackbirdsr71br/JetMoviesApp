package com.example.remote.data.remote.genre

data class GenreResponse(
    val genres: List<Genre>
)
data class Genre(
    val id: Int,
    val name: String
)