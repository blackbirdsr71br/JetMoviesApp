package com.example.local.domain.repository

import com.example.local.data.local.entities.GenresEntity
import kotlinx.coroutines.flow.Flow

interface GenresRepository {

    suspend fun insert(genre: GenresEntity)

    suspend fun delete(genre: GenresEntity)

    fun getGenresList(): Flow<List<GenresEntity>>
}
