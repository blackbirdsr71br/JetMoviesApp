package com.example.jetmoviesapp.domain.repository

import com.example.jetmoviesapp.common.Resource
import com.example.jetmoviesapp.data.remote.genre.GenreResponse
import com.example.jetmoviesapp.data.remote.latest.LatestResponse
import com.example.jetmoviesapp.data.remote.movie.MovieResponse
import com.example.jetmoviesapp.domain.model.HomeType
import com.example.jetmoviesapp.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {

    suspend fun getTopRatedMovies(page: Int): MovieResponse

    suspend fun getNowPlayingMovies(page: Int): MovieResponse

    suspend fun getLatestMovies(): LatestResponse

    suspend fun getGenres(): GenreResponse

    suspend fun getPopularMovies(page: Int): MovieResponse

    fun getMovieById(id: Int): Flow<Resource<MovieDetail>>

    suspend fun getMovieWithGenres(page: Int, genreId: Int): MovieResponse

    suspend fun searchMovie(page: Int, query: String): MovieResponse

    fun getHomeMovies(): Flow<Resource<List<HomeType>>>
}
