package com.example.jetmoviesapp.domain.usecases.dataBaseUseCases

import com.example.jetmoviesapp.domain.repository.MoviesRepository

class GetMovieByIdUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke(id: Int) = repository.getMovieById(id)
}
