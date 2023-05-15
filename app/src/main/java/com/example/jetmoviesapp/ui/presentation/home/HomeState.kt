package com.example.jetmoviesapp.ui.presentation.home

import com.example.remote.domain.model.HomeType

data class HomeState(
    val homeList: List<com.example.remote.domain.model.HomeType> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
