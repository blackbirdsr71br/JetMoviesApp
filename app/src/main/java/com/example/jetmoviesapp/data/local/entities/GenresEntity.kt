package com.example.jetmoviesapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "tbl_genres")
data class GenresEntity(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "name") val name: String,
)

