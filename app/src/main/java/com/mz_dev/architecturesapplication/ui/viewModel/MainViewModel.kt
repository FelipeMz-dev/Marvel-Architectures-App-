package com.mz_dev.architecturesapplication.ui.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mz_dev.architecturesapplication.data.Character
import com.mz_dev.architecturesapplication.data.MarvelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MarvelRepository ) : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            repository.getCharacters()

            repository.characters.collect {
                _state.value = UiState(characters = it)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun onChangeFavorite(character: Character) {
        viewModelScope.launch {
            repository.updateCharacter(character.copy(favorite = !character.favorite))
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val characters: List<Character> = emptyList()
    )
}