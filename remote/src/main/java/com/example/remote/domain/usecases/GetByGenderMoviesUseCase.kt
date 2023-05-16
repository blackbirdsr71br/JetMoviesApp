package com.example.remote.domain.usecases

import com.example.remote.domain.repository.NetworkRepository

class GetByGenderMoviesUseCase(private val repository: NetworkRepository) {
    suspend operator fun invoke(page: Int, genreId: Int) = repository.getMovieWithGenres(page, genreId)
}
