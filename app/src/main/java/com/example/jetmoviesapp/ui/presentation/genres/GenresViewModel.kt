package com.example.jetmoviesapp.ui.presentation.genres

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetmoviesapp.data.remote.genre.Genre
import com.example.jetmoviesapp.domain.repository.NetworkRepository
import com.example.jetmoviesapp.domain.usecases.useCaseNetwork
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetGenresMovies(private val repository: NetworkRepository) {
    suspend operator fun invoke() = repository.getGenres().genres
}

@HiltViewModel
class GenresViewModel @Inject constructor(
    private val useCase: useCaseNetwork,
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
