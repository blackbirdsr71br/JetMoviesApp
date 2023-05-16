package com.example.remote.domain.usecases


import com.example.remote.data.paging.SearchMovie
import com.example.remote.domain.repository.NetworkRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCasesNetworkModule {

    @Provides
    fun usecases(repository: NetworkRepository) = UseCaseNetwork(
        getMoviesHome = GetHomeMoviesUseCase(repository),
        getGenresMovies = GetGenresMoviesUseCase(repository),
        getNowPlayMovies = GetNowPlayMoviesUseCase(repository),
        getTopRatedMovies = GetTopRatedMoviesUseCase(repository),
        getMoviesByGenderMovies = GetByGenderMoviesUseCase(repository),
        getPopularMovies = GetPopularMoviesUseCase(repository),
        getLatestMovies = GetLatestMoviesUseCase(repository),
        searchMovie = SearchMovie(repository),
        getMoviebyId = GetMovieByIdNetworkUseCase(repository)

    )
}



data class UseCaseNetwork(
    val getLatestMovies: GetLatestMoviesUseCase,
    val getMoviesHome: GetHomeMoviesUseCase,
    val getGenresMovies: GetGenresMoviesUseCase,
    val getTopRatedMovies: GetTopRatedMoviesUseCase,
    val getNowPlayMovies: GetNowPlayMoviesUseCase,
    val getPopularMovies: GetPopularMoviesUseCase,
    val getMoviesByGenderMovies: GetByGenderMoviesUseCase,
    val searchMovie: SearchMovie,
    val getMoviebyId: GetMovieByIdNetworkUseCase
)
