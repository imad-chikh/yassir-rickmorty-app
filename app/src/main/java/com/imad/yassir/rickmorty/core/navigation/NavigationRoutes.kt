package com.imad.yassir.rickmorty.core.navigation

/**
 * Navigation routes for the app
 */
object NavigationRoutes {
    const val CHARACTER_LIST = "character_list"
    const val CHARACTER_DETAIL = "character_detail/{characterId}"
    
    /**
     * Creates the character detail route with the given character ID
     */
    fun characterDetail(characterId: Int): String {
        return "character_detail/$characterId"
    }
}

/**
 * Navigation arguments for type-safe navigation
 */
object NavigationArgs {
    const val CHARACTER_ID = "characterId"
}

/**
 * Sealed class representing all possible screens in the app
 */
sealed class Screen(val route: String) {
    object CharacterList : Screen(NavigationRoutes.CHARACTER_LIST)
    object CharacterDetail : Screen(NavigationRoutes.CHARACTER_DETAIL)
} 