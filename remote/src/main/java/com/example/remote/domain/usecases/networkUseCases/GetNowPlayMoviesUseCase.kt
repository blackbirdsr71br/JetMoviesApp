package com.example.remote.domain.usecases.networkUseCases

import com.example.remote.domain.repository.NetworkRepository

class GetNowPlayMoviesUseCase(private val repository: com.example.remote.domain.repository.NetworkRepository) {
    suspend operator fun invoke(page: Int) = repository.getNowPlayingMovies(page)
}
