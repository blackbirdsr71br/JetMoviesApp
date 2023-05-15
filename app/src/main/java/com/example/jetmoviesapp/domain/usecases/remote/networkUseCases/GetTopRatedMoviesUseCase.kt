package com.example.jetmoviesapp.domain.usecases.remote.networkUseCases

import com.example.remote.domain.repository.NetworkRepository

class GetTopRatedMoviesUseCase(private val repository: com.example.remote.domain.repository.NetworkRepository) {
    suspend operator fun invoke(page: Int) = repository.getTopRatedMovies(page)
}
