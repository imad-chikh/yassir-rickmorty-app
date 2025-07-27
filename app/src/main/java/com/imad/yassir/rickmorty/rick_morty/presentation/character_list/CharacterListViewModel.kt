package com.imad.yassir.rickmorty.rick_morty.presentation.character_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imad.yassir.rickmorty.core.domain.util.onError
import com.imad.yassir.rickmorty.core.domain.util.onSuccess
import com.imad.yassir.rickmorty.rick_morty.domain.CharacterDataSource
import com.imad.yassir.rickmorty.rick_morty.presentation.models.toCharacterUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val charaterListDataSource: CharacterDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterListState())
    val state = _state.onStart { loadCharacterList() }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        CharacterListState()
    )


    private val _events = Channel<CharacterListEvent>()
    val events = _events.receiveAsFlow()


    fun onAction(action: CharacterListAction) {
//        when(action) {
//            is CharacterListAction.LoadCharacters -> {
//
//            }
//        }
    }

    //load character list
    private suspend fun loadCharacterList() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
        }

        charaterListDataSource
            .getCharacters()
            .onSuccess { items ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        characters = items.map { it.toCharacterUi() }
                    )
                }
            }
            .onError { error ->
                _state.update { it.copy(isLoading = false) }
                _events.send(CharacterListEvent.ShowError(error.toString()))
        }
    }

}