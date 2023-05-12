package com.example.jetmoviesapp.domain.usecases.dataBaseUseCases

import com.example.jetmoviesapp.data.local.entities.MovieEntity
import com.example.jetmoviesapp.domain.repository.MoviesRepository

class InsertMovieUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke(movie: MovieEntity) = repository.insert(movie)
}
