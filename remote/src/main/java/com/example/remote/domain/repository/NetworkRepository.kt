package com.example.remote.domain.repository

import com.example.common.Resource
import com.example.remote.domain.model.HomeType
import com.example.remote.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {

    suspend fun getTopRatedMovies(page: Int): com.example.remote.data.remote.movie.MovieResponse

    suspend fun getNowPlayingMovies(page: Int): com.example.remote.data.remote.movie.MovieResponse

    suspend fun getLatestMovies(page: Int): com.example.remote.data.remote.movie.MovieResponse

    suspend fun getGenres(): com.example.remote.data.remote.genre.GenreResponse

    suspend fun getPopularMovies(page: Int): com.example.remote.data.remote.movie.MovieResponse

    fun getMovieByIdN(id: Int): Flow<Resource<MovieDetail>>

    suspend fun getMovieWithGenres(page: Int, genreId: Int): com.example.remote.data.remote.movie.MovieResponse

    suspend fun searchMovie(page: Int, query: String): com.example.remote.data.remote.movie.MovieResponse

    fun getHomeMovies(): Flow<Resource<List<HomeType>>>
}
