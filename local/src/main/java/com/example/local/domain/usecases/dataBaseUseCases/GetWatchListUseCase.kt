package com.example.local.domain.usecases.dataBaseUseCases

import com.example.local.domain.repository.MoviesRepository

class GetWatchListUseCase(private val repository: MoviesRepository) {
    operator fun invoke() = repository.getWatchList()
}
