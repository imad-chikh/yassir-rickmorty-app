package com.imad.yassir.rickmorty.rick_morty.presentation.character_list


sealed class CharacterListEvent {
    data class NavigateToCharacterDetail(
        val characterId: Int,
        val characterName: String,
        val characterImage: String,
        val characterStatus: String,
        val characterSpecies: String,
    ) : CharacterListEvent()

    data class ShowError(val message: String) : CharacterListEvent()
    data class ShowToast(val message: String) : CharacterListEvent()
}