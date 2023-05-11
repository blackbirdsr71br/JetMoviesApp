package com.example.jetmoviesapp.domain.usecases.dataBaseUseCases

import com.example.jetmoviesapp.domain.repository.MoviesRepository

class GetWatchList(private val repository: MoviesRepository) {
    operator fun invoke() = repository.getWatchList()
}