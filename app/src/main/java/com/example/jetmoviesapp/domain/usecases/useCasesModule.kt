package com.example.jetmoviesapp.domain.usecases

import com.example.jetmoviesapp.data.local.entities.MovieEntity
import com.example.jetmoviesapp.data.paging.SearchMovie
import com.example.jetmoviesapp.domain.repository.MoviesRepository
import com.example.jetmoviesapp.domain.repository.NetworkRepository
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

class InsertMovie(private val repository: MoviesRepository) {
    suspend operator fun invoke(movie: MovieEntity) = repository.insert(movie)
}

class DeleteMovie(private val repository: MoviesRepository) {
    suspend operator fun invoke(movie: MovieEntity) = repository.delete(movie)
}

class GetWatchList(private val repository: MoviesRepository) {
    operator fun invoke() = repository.getWatchList()
}

class GetMovieById(private val repository: MoviesRepository) {
    suspend operator fun invoke(id: Int) = repository.getMovieById(id)
}

class GetMovieByIdN(private val repository: NetworkRepository) {
    suspend operator fun invoke(id: Int) = repository.getMovieByIdN(id)
}
