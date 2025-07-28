package com.imad.yassir.rickmorty.core.navigation

import androidx.navigation.NavBackStackEntry

/**
 * Extension function to safely extract character ID from navigation arguments
 */
fun NavBackStackEntry.getCharacterId(): Int {
    return arguments?.getString(NavigationArgs.CHARACTER_ID)?.toIntOrNull() ?: 0
}

/**
 * Extension function to check if character ID is valid
 */
fun NavBackStackEntry.hasValidCharacterId(): Boolean {
    return getCharacterId() > 0
} 