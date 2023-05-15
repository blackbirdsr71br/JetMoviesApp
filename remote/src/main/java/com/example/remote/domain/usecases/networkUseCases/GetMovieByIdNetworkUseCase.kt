package com.example.remote.domain.usecases.networkUseCases

import com.example.remote.domain.repository.NetworkRepository

class GetMovieByIdNetworkUseCase(private val repository: com.example.remote.domain.repository.NetworkRepository) {
    suspend operator fun invoke(id: Int) = repository.getMovieByIdN(id)
}
