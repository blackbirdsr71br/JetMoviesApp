package com.example.local.domain.usecases

import com.example.local.domain.repository.MoviesRepository

class GetMovieByIdUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke(id: Int) = repository.getMovieById(id)
}
