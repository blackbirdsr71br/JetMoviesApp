package com.example.jetmoviesapp.domain.usecases.remote.networkUseCases

import com.example.jetmoviesapp.domain.repository.NetworkRepository

class GetPopularMoviesUseCase(private val repository: NetworkRepository) {
    suspend operator fun invoke(page: Int) = repository.getPopularMovies(page)
}
