package com.example.jetmoviesapp.domain.usecases.remote.networkUseCases

import com.example.remote.domain.repository.NetworkRepository

class GetGenresMoviesUseCase(private val repository: com.example.remote.domain.repository.NetworkRepository) {
    suspend operator fun invoke() = repository.getGenres().genres
}
