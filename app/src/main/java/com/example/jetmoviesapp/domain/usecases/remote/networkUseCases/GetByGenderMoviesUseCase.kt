package com.example.jetmoviesapp.domain.usecases.remote.networkUseCases

import com.example.remote.domain.repository.NetworkRepository

class GetByGenderMoviesUseCase(private val repository: com.example.remote.domain.repository.NetworkRepository) {
    suspend operator fun invoke(page: Int, genreId: Int) = repository.getMovieWithGenres(page, genreId)
}
