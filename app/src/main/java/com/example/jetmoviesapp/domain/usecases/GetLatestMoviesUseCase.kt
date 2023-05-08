package com.example.jetmoviesapp.domain.usecases

import com.example.jetmoviesapp.domain.repository.NetworkRepository

class GetLatestMovies(private val repository: NetworkRepository) {
    suspend operator fun invoke(page: Int) = repository.getLatestMovies(page)
}