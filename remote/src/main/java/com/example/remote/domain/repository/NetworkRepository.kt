package com.example.remote.domain.repository

import com.example.jetmoviesapp.common.Resource
import com.example.remote.remote.genre.GenreResponse
import com.example.remote.remote.movie.MovieResponse
import com.example.remote.domain.model.HomeType
import com.example.remote.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {

    suspend fun getTopRatedMovies(page: Int): com.example.remote.remote.movie.MovieResponse

    suspend fun getNowPlayingMovies(page: Int): com.example.remote.remote.movie.MovieResponse

    suspend fun getLatestMovies(page: Int): com.example.remote.remote.movie.MovieResponse

    suspend fun getGenres(): com.example.remote.remote.genre.GenreResponse

    suspend fun getPopularMovies(page: Int): com.example.remote.remote.movie.MovieResponse

    fun getMovieByIdN(id: Int): Flow<Resource<MovieDetail>>

    suspend fun getMovieWithGenres(page: Int, genreId: Int): com.example.remote.remote.movie.MovieResponse

    suspend fun searchMovie(page: Int, query: String): com.example.remote.remote.movie.MovieResponse

    fun getHomeMovies(): Flow<Resource<List<HomeType>>>
}
