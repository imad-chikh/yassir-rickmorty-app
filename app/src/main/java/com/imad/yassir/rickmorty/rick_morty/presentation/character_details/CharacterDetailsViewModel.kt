package com.imad.yassir.rickmorty.rick_morty.presentation.character_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imad.yassir.rickmorty.rick_morty.domain.CharacterDetails
import com.imad.yassir.rickmorty.rick_morty.domain.CharacterDataSource
import com.imad.yassir.rickmorty.core.domain.util.NetworkError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(
    private val characterDataSource: CharacterDataSource,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val characterId: Int = savedStateHandle.get<String>("characterId")?.toIntOrNull() ?: 0

    private val _state = MutableStateFlow(CharacterDetailsState())
    val state: StateFlow<CharacterDetailsState> = _state.asStateFlow()

    init {
        loadCharacterDetails()
    }

    private fun loadCharacterDetails() {
        if (characterId == 0) {
            _state.value = _state.value.copy(
                isLoading = false,
                error = "Invalid character ID"
            )
            return
        }

        _state.value = _state.value.copy(isLoading = true, error = null)

        viewModelScope.launch {
            when (val result = characterDataSource.getCharacterDetails(characterId)) {
                is com.imad.yassir.rickmorty.core.domain.util.Result.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        characterDetails = result.data,
                        error = null
                    )
                }
                is com.imad.yassir.rickmorty.core.domain.util.Result.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = when (result.error) {
                            NetworkError.NO_INTERNET -> "No internet connection"
                            NetworkError.SERVER_ERROR -> "Server error occurred"
                            NetworkError.UNKNOWN -> "Unknown error occurred"
                            NetworkError.REQUEST_TIMEOUT -> "Request timeout"
                            NetworkError.TOO_MANY_REQUESTS -> "Too many requests"
                            NetworkError.SERIALIZATION -> "Data serialization error"
                        }
                    )
                }
            }
        }
    }

    fun retry() {
        loadCharacterDetails()
    }
}

data class CharacterDetailsState(
    val isLoading: Boolean = true,
    val characterDetails: CharacterDetails? = null,
    val error: String? = null
) 