package com.imad.yassir.rickmorty.rick_morty.presentation.character_list

import com.imad.yassir.rickmorty.core.navigation.NavigationManager

sealed class CharacterListEvent {
    data class NavigateToCharacterDetail(
        val characterId: Int
    ) : CharacterListEvent()

    data class ShowError(val message: String) : CharacterListEvent()
    data class ShowToast(val message: String) : CharacterListEvent()
}

/**
 * Extension function to handle navigation events
 */
fun CharacterListEvent.handleNavigation(navigationManager: NavigationManager) {
    when (this) {
        is CharacterListEvent.NavigateToCharacterDetail -> {
            navigationManager.navigateToCharacterDetail(characterId)
        }
        is CharacterListEvent.ShowError -> {
            // Error handling is done in the UI layer
        }
        is CharacterListEvent.ShowToast -> {
            // Toast handling is done in the UI layer
        }
    }
}