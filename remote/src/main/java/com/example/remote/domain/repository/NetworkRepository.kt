package com.example.remote.domain.repository

import com.example.common.Resource
import com.example.remote.data.remote.movie.MovieResponse
import com.example.remote.domain.model.HomeType
import com.example.remote.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {

    suspend fun getTopRatedMovies(page: Int): MovieResponse

    suspend fun getNowPlayingMovies(page: Int): MovieResponse

    suspend fun getLatestMovies(page: Int): MovieResponse

    suspend fun getGenres(): com.example.remote.data.remote.genre.GenreResponse

    suspend fun getPopularMovies(page: Int): MovieResponse

    //suspend fun getPopularMovies(page: Int): Flow<Resource<List<HomeType.Popular>>>



    fun getMovieByIdN(id: Int): Flow<Resource<MovieDetail>>

    suspend fun getMovieWithGenres(page: Int, genreId: Int): MovieResponse

    suspend fun searchMovie(page: Int, query: String): MovieResponse

    fun getHomeMovies(): Flow<Resource<List<HomeType>>>
}
