package com.example.remote.domain.usecases

import com.example.remote.domain.repository.NetworkRepository

class GetMovieByIdNetworkUseCase(private val repository: NetworkRepository) {
    operator fun invoke(id: Int) = repository.getMovieByIdN(id)
}
