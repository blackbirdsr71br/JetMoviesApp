package com.example.jetmoviesapp.domain.usecases

import com.example.jetmoviesapp.domain.repository.NetworkRepository

class GetGenresMovies(private val repository: NetworkRepository) {
    suspend operator fun invoke() = repository.getGenres().genres
}
