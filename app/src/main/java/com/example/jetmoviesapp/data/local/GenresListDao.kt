package com.example.jetmoviesapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jetmoviesapp.data.local.entities.GenresEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GenresListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(genre: GenresEntity)

    @Delete
    suspend fun delete(genre: GenresEntity)

    @Query("SELECT * FROM tbl_genres")
    fun getGenres(): Flow<List<GenresEntity>>

}
