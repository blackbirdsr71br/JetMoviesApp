package com.example.jetmoviesapp.domain.usecases.networkUseCases

import com.example.jetmoviesapp.domain.repository.NetworkRepository

class GetPopularMovies(private val repository: NetworkRepository) {
    suspend operator fun invoke(page: Int) = repository.getPopularMovies(page)
}