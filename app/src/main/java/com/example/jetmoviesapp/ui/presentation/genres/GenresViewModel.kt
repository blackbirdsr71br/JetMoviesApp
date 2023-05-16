package com.example.jetmoviesapp.ui.presentation.genres

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remote.data.remote.genre.Genre
import com.example.remote.domain.usecases.networkUseCases.UseCaseNetwork
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(
    private val useCase: UseCaseNetwork
) : ViewModel() {

    private val _stateGenres = mutableStateOf<List<Genre>>(emptyList())
    val stateGenres: State<List<Genre>> get() = _stateGenres

    init {
        getGenres()
    }

    private fun getGenres() {
        viewModelScope.launch {
            _stateGenres.value = useCase.getGenresMovies.invoke()
        }
    }
}
