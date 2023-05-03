package com.example.jetmoviesapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_genres")
data class GenresEntity(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "name") val name: String
)
