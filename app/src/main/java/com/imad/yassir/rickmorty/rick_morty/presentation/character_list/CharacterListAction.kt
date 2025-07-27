package com.imad.yassir.rickmorty.rick_morty.presentation.character_list

import com.imad.yassir.rickmorty.rick_morty.presentation.models.CharacterUi

sealed class CharacterListAction {
    object LoadCharacters : CharacterListAction()
    object LoadMoreCharacters : CharacterListAction()
    data class SearchCharacters(val query: String) : CharacterListAction()
    data class SelectCharacter(val character: CharacterUi) : CharacterListAction()
    object ClearError : CharacterListAction()
    object RetryLoad : CharacterListAction()
    object ClearSearch : CharacterListAction()
}
