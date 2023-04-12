package com.example.jetmoviesapp.presentation.detail

import com.example.jetmoviesapp.domain.model.MovieDetail

data class MovieDetailState(
    val movie: MovieDetail? = null,
    val isLoading: Boolean = false,
    val error: String = "",
    val isBookmarked: Boolean = false
)
