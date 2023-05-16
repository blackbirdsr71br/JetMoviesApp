package com.example.remote.domain.usecases

import com.example.remote.domain.repository.NetworkRepository

class GetLatestMoviesUseCase(private val repository: NetworkRepository) {
    suspend operator fun invoke(page: Int) = repository.getLatestMovies(page)
}
