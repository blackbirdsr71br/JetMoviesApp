package com.example.jetmoviesapp.domain.usecases.networkUseCases

import com.example.jetmoviesapp.data.paging.SearchMovie
import com.example.jetmoviesapp.domain.repository.MoviesRepository
import com.example.jetmoviesapp.domain.repository.NetworkRepository
import com.example.jetmoviesapp.domain.usecases.dataBaseUseCases.DeleteMovie
import com.example.jetmoviesapp.domain.usecases.dataBaseUseCases.GetMovieById
import com.example.jetmoviesapp.domain.usecases.dataBaseUseCases.GetWatchList
import com.example.jetmoviesapp.domain.usecases.dataBaseUseCases.InsertMovie
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCasesNetworkModule {

    @Provides
    fun usecases(repository: NetworkRepository) = UseCaseNetwork(
        getMoviesHome = GetHomeMovies(repository),
        getGenresMovies = GetGenresMovies(repository),
        getNowPlayMovies = GetNowPlayMovies(repository),
        getTopRatedMovies = GetTopRatedMovies(repository),
        getMoviesByGenderMovies = GetByGenderMovies(repository),
        getPopularMovies = GetPopularMovies(repository),
        getLatestMovies = GetLatestMovies(repository),
        searchMovie = SearchMovie(repository),
        getMoviebyId = GetMovieByIdN(repository),

    )
}

@Module
@InstallIn(SingletonComponent::class)
class UseCasesMovies {
    @Provides
    fun usecases(repository: MoviesRepository) = UseCaseMovie(
        InsertMovie(repository),
        DeleteMovie(repository),
        GetWatchList(repository),
        GetMovieById(repository),
    )
}

data class UseCaseMovie(
    val insert: InsertMovie,
    val deleteMovie: DeleteMovie,
    val getWatchList: GetWatchList,
    val getMoviebyId: GetMovieById,
)

data class UseCaseNetwork(
    val getLatestMovies: GetLatestMovies,
    val getMoviesHome: GetHomeMovies,
    val getGenresMovies: GetGenresMovies,
    val getTopRatedMovies: GetTopRatedMovies,
    val getNowPlayMovies: GetNowPlayMovies,
    val getPopularMovies: GetPopularMovies,
    val getMoviesByGenderMovies: GetByGenderMovies,
    val searchMovie: SearchMovie,
    val getMoviebyId: GetMovieByIdN,
)



