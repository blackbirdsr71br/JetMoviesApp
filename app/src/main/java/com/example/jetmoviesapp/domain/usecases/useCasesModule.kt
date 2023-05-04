package com.example.jetmoviesapp.domain.usecases

import com.example.jetmoviesapp.data.local.entities.MovieEntity
import com.example.jetmoviesapp.data.paging.GetByGenderMovies
import com.example.jetmoviesapp.data.paging.GetLatestMovies
import com.example.jetmoviesapp.data.paging.GetNowPlayMovies
import com.example.jetmoviesapp.data.paging.GetPopularMovies
import com.example.jetmoviesapp.data.paging.GetTopRatedMovies
import com.example.jetmoviesapp.data.paging.SearchMovie
import com.example.jetmoviesapp.domain.repository.MoviesRepository
import com.example.jetmoviesapp.domain.repository.NetworkRepository
import com.example.jetmoviesapp.ui.presentation.genres.GetGenresMovies
import com.example.jetmoviesapp.ui.presentation.home.getHomeMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCasesNetworkModule {

    @Provides
    fun usecases(repository: NetworkRepository) = useCaseNetwork(
        getMoviesHome = getHomeMovies(repository),
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
class useCasesMovies {
    @Provides
    fun usecases(repository: MoviesRepository) = useCaseMovie(
        insertMovie(repository),
        deleteMovie(repository),
        getWatchList(repository),
        getMovieById(repository),
    )
}

data class useCaseMovie(
    val insert: insertMovie,
    val deleteMovie: deleteMovie,
    val getWatchList: getWatchList,
    val getMoviebyId: getMovieById,
)

data class useCaseNetwork(
    val getLatestMovies: GetLatestMovies,
    val getMoviesHome: getHomeMovies,
    val getGenresMovies: GetGenresMovies,
    val getTopRatedMovies: GetTopRatedMovies,
    val getNowPlayMovies: GetNowPlayMovies,
    val getPopularMovies: GetPopularMovies,
    val getMoviesByGenderMovies: GetByGenderMovies,
    val searchMovie: SearchMovie,
    val getMoviebyId: GetMovieByIdN,
)

class insertMovie(private val repository: MoviesRepository) {
    suspend operator fun invoke(movie: MovieEntity) = repository.insert(movie)
}

class deleteMovie(private val repository: MoviesRepository) {
    suspend operator fun invoke(movie: MovieEntity) = repository.delete(movie)
}

class getWatchList(private val repository: MoviesRepository) {
    operator fun invoke() = repository.getWatchList()
}

class getMovieById(private val repository: MoviesRepository) {
    suspend operator fun invoke(id: Int) = repository.getMovieById(id)
}

class GetMovieByIdN(private val repository: NetworkRepository) {
    suspend operator fun invoke(id: Int) = repository.getMovieByIdN(id)
}
