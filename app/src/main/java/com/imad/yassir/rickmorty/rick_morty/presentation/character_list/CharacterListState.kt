package com.imad.yassir.rickmorty.rick_morty.presentation.character_list

import com.imad.yassir.rickmorty.rick_morty.presentation.models.CharacterUi

data class CharacterListState(
    val characters: List<CharacterUi> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false
    //TODO:add other items
)
