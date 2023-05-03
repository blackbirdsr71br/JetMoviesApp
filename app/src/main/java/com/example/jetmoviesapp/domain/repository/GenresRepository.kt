package com.example.jetmoviesapp.domain.repository

import com.example.jetmoviesapp.data.local.entities.GenresEntity
import com.example.jetmoviesapp.data.local.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

interface GenresRepository {

    suspend fun insert(genre: GenresEntity)

    suspend fun delete(genre: GenresEntity)

    fun getGenresList(): Flow<List<GenresEntity>>
}
