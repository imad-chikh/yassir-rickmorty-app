package com.imad.yassir.rickmorty.rick_morty.presentation.character_list

import com.imad.yassir.rickmorty.rick_morty.presentation.models.CharacterUi

sealed class CharacterListAction {
    data class SearchCharacters(val query: String) : CharacterListAction()
    object LoadMoreCharacters : CharacterListAction()
    object RefreshCharacters : CharacterListAction()
    data class OnCharacterClick(
        val characterId: Int
    ) : CharacterListAction()

}
