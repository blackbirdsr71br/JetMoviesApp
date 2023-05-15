package com.example.remote.domain.usecases.networkUseCases

import com.example.remote.domain.repository.NetworkRepository

class GetTopRatedMoviesUseCase(private val repository: NetworkRepository) {
    suspend operator fun invoke(page: Int) = repository.getTopRatedMovies(page)
}
