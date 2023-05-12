package com.example.jetmoviesapp.domain.usecases.networkUseCases

import com.example.jetmoviesapp.domain.repository.NetworkRepository

class GetHomeMoviesUseCase(private val repository: NetworkRepository) {
    suspend operator fun invoke() = repository.getHomeMovies()
}
