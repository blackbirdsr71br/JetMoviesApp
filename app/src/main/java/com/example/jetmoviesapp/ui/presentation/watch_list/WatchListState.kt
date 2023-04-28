package com.example.jetmoviesapp.ui.presentation.watch_list

import com.example.jetmoviesapp.data.local.entities.MovieEntity

data class WatchListState(
    val list: MutableList<MovieEntity> = mutableListOf(),
    val isEmpty: Boolean = true,
    val isLoading: Boolean = false,
    val error: String = ""
)
