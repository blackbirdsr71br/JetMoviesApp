package com.example.jetmoviesapp.domain.usecases.networkUseCases

import com.example.jetmoviesapp.domain.repository.NetworkRepository

class GetTopRatedMovies(private val repository: NetworkRepository) {
    suspend operator fun invoke(page: Int) = repository.getTopRatedMovies(page)
}