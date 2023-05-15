package com.example.jetmoviesapp.domain.usecases.local.dataBaseUseCases

import com.example.local.domain.repository.MoviesRepository

class GetWatchListUseCase(private val repository: MoviesRepository) {
    operator fun invoke() = repository.getWatchList()
}
