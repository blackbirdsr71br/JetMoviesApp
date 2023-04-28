package com.example.jetmoviesapp.ui.presentation.home

import com.example.jetmoviesapp.domain.model.HomeType

data class HomeState(
    val homeList : List<HomeType> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)