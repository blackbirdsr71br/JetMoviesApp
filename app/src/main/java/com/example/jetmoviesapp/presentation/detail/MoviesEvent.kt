package com.example.jetmoviesapp.presentation.detail

import com.example.jetmoviesapp.data.local.entities.MovieEntity


sealed class MoviesEvent {
    data class BookmarkMovie(val movie: MovieEntity) : MoviesEvent()
    data class DeleteMovie(val movie: MovieEntity): MoviesEvent()
    data class IsBookmarked(val id: Int): MoviesEvent()
}