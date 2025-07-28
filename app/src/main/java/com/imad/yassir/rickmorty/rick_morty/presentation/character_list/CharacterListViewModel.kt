package com.imad.yassir.rickmorty.rick_morty.presentation.character_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imad.yassir.rickmorty.core.domain.util.onError
import com.imad.yassir.rickmorty.core.domain.util.onSuccess
import com.imad.yassir.rickmorty.rick_morty.domain.CharacterDataSource
import com.imad.yassir.rickmorty.rick_morty.presentation.models.toCharacterUi
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
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
        when (action) {
            is CharacterListAction.LoadMoreCharacters -> {
                loadMoreCharacters()
            }

            is CharacterListAction.SearchCharacters -> {
                searchCharacters(action.query)
            }

            is CharacterListAction.RefreshCharacters -> {
                //TODO: implement      refreshCharacters()
            }

            is CharacterListAction.OnCharacterClick -> {
                Log.d("CharacterListViewModel","------------------Hi-----------------")
                viewModelScope.launch {
                    Log.d("CharacterListViewModel","------------------Clicked on ${action.characterId}-----------------")

                    _events.send(CharacterListEvent.NavigateToCharacterDetail(action.characterId))
                }

            }
        }
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
            .getCharacters(1)
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

    //load more characters for pagination
    private fun loadMoreCharacters() {
        val currentState = _state.value
        if (currentState.isLoadingMore || !currentState.canLoadMore) return

        viewModelScope.launch {
            _state.update {
                it.copy(isLoadingMore = true)
            }

            val nextPage = currentState.currentPage + 1

            charaterListDataSource
                .getCharacters(nextPage)
                .onSuccess { newItems ->
                    _state.update {
                        it.copy(
                            isLoadingMore = false,
                            characters = it.characters + newItems.map { it.toCharacterUi() },
                            currentPage = nextPage,
                            canLoadMore = newItems.isNotEmpty()
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoadingMore = false,
                            canLoadMore = false
                        )
                    }
                    _events.send(CharacterListEvent.ShowError("Failed to load more: ${error}"))
                }
        }
    }
    // Search characters

    private var searchJob: Job? = null

    private fun searchCharacters(query: String) {
        searchJob?.cancel()
        _state.update {
            it.copy(
                searchQuery = query,
                isSearchMode = query.isNotBlank()
            )
        }

        if (query.isBlank()) {
            // Return to normal mode, show all characters
            _state.update {
                it.copy(
                    searchResults = emptyList(),
                    isSearching = false
                )
            }
            return
        }

        // Implement API search
        searchJob =    viewModelScope.launch {
            delay(300) // Debounce for 300ms

            _state.update {
                it.copy(isSearching = true)
            }

            charaterListDataSource
                .searchCharacter(query)
                .onSuccess { searchResults ->

                    _state.update {
                        it.copy(
                            searchResults = searchResults.map { it.toCharacterUi() },
                            isSearching = false
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isSearching = false,
                            searchResults = emptyList()
                        )
                    }
                    _events.send(CharacterListEvent.ShowError("Search for $query failed: ${error}"))
                }
        }
    }

}