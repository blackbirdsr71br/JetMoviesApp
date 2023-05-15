package com.example.local.domain.usecases.dataBaseUseCases

import com.example.local.data.local.entities.MovieEntity
import com.example.local.domain.repository.MoviesRepository

class DeleteMovieUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke(movie: MovieEntity) = repository.delete(movie)
}
