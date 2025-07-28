package com.imad.yassir.rickmorty.core.navigation

import androidx.navigation.NavController
import androidx.navigation.NavHostController

/**
 * Centralized navigation manager for the app
 */
class NavigationManager(private val navController: NavHostController) {
    
    /**
     * Navigate to character list screen
     */
    fun navigateToCharacterList() {
        navController.navigate(NavigationRoutes.CHARACTER_LIST) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            popUpTo(NavigationRoutes.CHARACTER_LIST) {
                inclusive = true
            }
        }
    }
    
    /**
     * Navigate to character detail screen
     */
    fun navigateToCharacterDetail(characterId: Int) {
        navController.navigate(NavigationRoutes.characterDetail(characterId))
    }
    
    /**
     * Navigate back
     */
    fun navigateBack() {
            navController.popBackStack()
    }
    
    /**
     * Navigate back to character list
     */
    fun navigateBackToCharacterList() {
        navController.popBackStack(NavigationRoutes.CHARACTER_LIST, false)
    }
} 