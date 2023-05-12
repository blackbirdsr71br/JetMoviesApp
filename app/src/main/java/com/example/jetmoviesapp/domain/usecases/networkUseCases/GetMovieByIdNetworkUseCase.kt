package com.example.jetmoviesapp.domain.usecases.networkUseCases

import com.example.jetmoviesapp.domain.repository.NetworkRepository

class GetMovieByIdNetworkUseCase(private val repository: NetworkRepository) {
    suspend operator fun invoke(id: Int) = repository.getMovieByIdN(id)
}
