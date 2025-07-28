package com.imad.yassir.rickmorty.core.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.imad.yassir.rickmorty.core.presentation.util.ObserveAsEvents
import com.imad.yassir.rickmorty.rick_morty.presentation.character_details.CharacterDetailScreen
import com.imad.yassir.rickmorty.rick_morty.presentation.character_list.CharacterListEvent
import com.imad.yassir.rickmorty.rick_morty.presentation.character_list.CharacterListScreen
import com.imad.yassir.rickmorty.rick_morty.presentation.character_list.CharacterListViewModel
import com.imad.yassir.rickmorty.rick_morty.presentation.character_list.handleNavigation
import org.koin.androidx.compose.koinViewModel


@Composable
fun AdaptiveNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: CharacterListViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    
    // Create navigation manager
    val navigationManager = remember { NavigationManager(navController) }

    ObserveAsEvents(events = viewModel.events) { event ->
        when (event) {
            is CharacterListEvent.ShowError -> {
                Toast.makeText(
                    context,
                    event.message,
                    Toast.LENGTH_LONG
                ).show()
            }

            is CharacterListEvent.NavigateToCharacterDetail -> {
                event.handleNavigation(navigationManager)
            }

            is CharacterListEvent.ShowToast -> {
                Toast.makeText(
                    context,
                    event.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.CHARACTER_LIST,
        modifier = modifier
    ) {
        composable(NavigationRoutes.CHARACTER_LIST) {
            CharacterListScreen(
                state = state,
                onAction = viewModel::onAction
            )
        }

        composable(NavigationRoutes.CHARACTER_DETAIL) { backStackEntry ->
            if (backStackEntry.hasValidCharacterId()) {
                CharacterDetailScreen(
                    onNavigateBack = {
                        navigationManager.navigateBack()
                    }
                )
            } else {
                // Handle invalid character ID - navigate back or show error
                navigationManager.navigateBack()
            }
        }
    }
}