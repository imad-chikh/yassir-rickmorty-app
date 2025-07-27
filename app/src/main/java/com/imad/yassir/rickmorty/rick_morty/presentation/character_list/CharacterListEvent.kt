package com.imad.yassir.rickmorty.rick_morty.presentation.character_list


sealed class CharacterListEvent {
    data class NavigateToCharacterDetail(val characterId: Int) : CharacterListEvent()
    data class ShowError(val message: String) : CharacterListEvent()
    data class ShowToast(val message: String) : CharacterListEvent()
}