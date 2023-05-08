package com.example.jetmoviesapp.domain.usecases

import com.example.jetmoviesapp.domain.repository.NetworkRepository

class GetByGenderMovies(private val repository: NetworkRepository) {
    suspend operator fun invoke(page: Int, genreId: Int) = repository.getMovieWithGenres(page, genreId)
}