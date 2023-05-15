package com.example.jetmoviesapp.domain.repository

import com.example.local.data.local.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun insert(movie: MovieEntity)

    suspend fun delete(movie: MovieEntity)

    fun getWatchList(): Flow<List<MovieEntity>>

    suspend fun getMovieById(id: Int): MovieEntity?
}
