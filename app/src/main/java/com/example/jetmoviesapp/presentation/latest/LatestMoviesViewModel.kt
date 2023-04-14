package com.example.jetmoviesapp.presentation.latest

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetmoviesapp.data.remote.latest.Latest
import com.example.jetmoviesapp.domain.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LatestMoviesViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
) : ViewModel() {

    private val _stateGenres = mutableStateOf<List<Latest>>(emptyList())
    val stateGenres: State<List<Latest>> get() = _stateGenres

    init {
        getGenres()
    }

    private fun getGenres() {
        viewModelScope.launch {
            _stateGenres.value = networkRepository.getLatestMovies().latest
        }
    }
}
