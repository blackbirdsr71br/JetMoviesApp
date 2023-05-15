package com.example.jetmoviesapp.domain.usecases.remote.networkUseCases

import com.example.jetmoviesapp.data.paging.SearchMovie
import com.example.local.domain.repository.MoviesRepository
import com.example.remote.domain.repository.NetworkRepository
import com.example.jetmoviesapp.domain.usecases.local.dataBaseUseCases.DeleteMovieUseCase
import com.example.jetmoviesapp.domain.usecases.local.dataBaseUseCases.GetMovieByIdUseCase
import com.example.jetmoviesapp.domain.usecases.local.dataBaseUseCases.GetWatchListUseCase
import com.example.jetmoviesapp.domain.usecases.local.dataBaseUseCases.InsertMovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCasesNetworkModule {

    @Provides
    fun usecases(repository: com.example.remote.domain.repository.NetworkRepository) = UseCaseNetwork(
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

@Module
@InstallIn(SingletonComponent::class)
class UseCasesMovies {
    @Provides
    fun usecases(repository: MoviesRepository) = UseCaseMovie(
        InsertMovieUseCase(repository),
        DeleteMovieUseCase(repository),
        GetWatchListUseCase(repository),
        GetMovieByIdUseCase(repository)
    )
}

data class UseCaseMovie(
    val insert: InsertMovieUseCase,
    val deleteMovie: DeleteMovieUseCase,
    val getWatchList: GetWatchListUseCase,
    val getMoviebyId: GetMovieByIdUseCase
)

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
