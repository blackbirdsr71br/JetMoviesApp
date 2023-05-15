package com.example.jetmoviesapp.ui.presentation.detail

import com.example.remote.domain.model.MovieDetail


data class MovieDetailState(
    val movie: MovieDetail? = null,
    val isLoading: Boolean = false,
    val error: String = "",
    val isBookmarked: Boolean = false
)
