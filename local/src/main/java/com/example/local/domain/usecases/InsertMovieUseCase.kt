package com.example.local.domain.usecases

import com.example.local.data.local.entities.MovieEntity
import com.example.local.domain.repository.MoviesRepository

class InsertMovieUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke(movie: MovieEntity) = repository.insert(movie)
}
