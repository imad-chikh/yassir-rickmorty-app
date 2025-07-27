package com.imad.yassir.rickmorty.rick_morty.presentation.character_list

import com.imad.yassir.rickmorty.rick_morty.presentation.models.CharacterUi

data class CharacterListState(
    val characters: List<CharacterUi> = emptyList(),
    val searchResults: List<CharacterUi> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val isRefreshing: Boolean = false,
    val isSearching: Boolean = false,
    val error: String? = null,
    val canLoadMore: Boolean = true,
    val currentPage: Int = 1,
    val isSearchMode: Boolean = false,
    val isEmpty: Boolean = false
)