package com.example.jetmoviesapp.ui.presentation.detail

import com.example.local.data.local.entities.MovieEntity

sealed class MoviesEvent {
    data class BookmarkMovie(val movie: MovieEntity) : MoviesEvent()
    data class DeleteMovie(val movie: MovieEntity) : MoviesEvent()
    data class IsBookmarked(val id: Int) : MoviesEvent()
}
