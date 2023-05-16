package com.example.local.domain.repository

import com.example.local.data.local.GenresListDao
import com.example.local.data.local.entities.GenresEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GenresRepositoryImpl @Inject constructor(
    private val dao: GenresListDao
) : GenresRepository {

    override suspend fun insert(genre: GenresEntity) {
        dao.insert(genre = genre)
    }

    override suspend fun delete(genre: GenresEntity) {
        dao.delete(genre = genre)
    }
    override fun getGenresList(): Flow<List<GenresEntity>> = dao.getGenres().flowOn(Dispatchers.IO)
}