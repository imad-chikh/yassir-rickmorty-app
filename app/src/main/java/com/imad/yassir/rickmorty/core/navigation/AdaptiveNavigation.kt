package com.imad.yassir.rickmorty.core.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import org.koin.androidx.compose.koinViewModel
import java.net.URLDecoder
import java.net.URLEncoder

@Composable
fun AdaptiveNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: CharacterListViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

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
                val encodedName = URLEncoder.encode(event.characterName, "UTF-8")
                val encodedImage = URLEncoder.encode(event.characterImage, "UTF-8")
                val encodedStatus = URLEncoder.encode(event.characterStatus, "UTF-8")
                val encodedSpecies = URLEncoder.encode(event.characterSpecies, "UTF-8")

                navController.navigate("character_detail/${event.characterId}/$encodedName/$encodedImage/$encodedStatus/$encodedSpecies")
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
        startDestination = "character_list",
        modifier = modifier
    ) {
        composable("character_list") {
            CharacterListScreen(
                state = state,
                onAction = viewModel::onAction
            )
        }

        composable("character_detail/{characterId}/{characterName}/{characterImage}/{characterStatus}/{characterSpecies}") { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString("characterId")?.toIntOrNull() ?: 0
            val characterName = backStackEntry.arguments?.getString("characterName")?.let {
                URLDecoder.decode(it, "UTF-8")
            } ?: ""
            val characterImage = backStackEntry.arguments?.getString("characterImage")?.let {
                URLDecoder.decode(it, "UTF-8")
            } ?: ""
            val characterStatus = backStackEntry.arguments?.getString("characterStatus")?.let {
                URLDecoder.decode(it, "UTF-8")
            } ?: "Unknown"
            val characterSpecies = backStackEntry.arguments?.getString("characterSpecies")?.let {
                URLDecoder.decode(it, "UTF-8")
            } ?: ""

            CharacterDetailScreen(
                characterId = characterId,
                characterName = characterName,
                characterImage = characterImage,
                characterStatus = characterStatus,
                characterSpecies = characterSpecies,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}