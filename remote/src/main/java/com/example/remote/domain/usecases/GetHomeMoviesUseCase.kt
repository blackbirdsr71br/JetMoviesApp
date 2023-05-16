package com.example.remote.domain.usecases

import com.example.remote.domain.repository.NetworkRepository

class GetHomeMoviesUseCase(private val repository: NetworkRepository) {
    operator fun invoke() = repository.getHomeMovies()
}
