package com.imad.yassir.rickmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.imad.yassir.rickmorty.rick_morty.presentation.character_list.CharacterListScreen
import com.imad.yassir.rickmorty.rick_morty.presentation.character_list.CharacterListState
import com.imad.yassir.rickmorty.rick_morty.presentation.models.CharacterUi
import com.imad.yassir.rickmorty.ui.theme.YassirRickMortyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YassirRickMortyAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    CharacterListScreen(
                        modifier = Modifier.padding(innerPadding),
                        onSearchQueryChanged = { },
                        state = CharacterListState(
                            isLoading = false,
                            characters = listOf(
                                CharacterUi(
                                    id = 1,
                                    name = "Rick Sanchez",
                                    species = "Human",
                                    imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
                                ),
                                CharacterUi(
                                    id = 2,
                                    name = "Morty Smith",
                                    species = "Human",
                                    imageUrl = "https://rickandmortyapi.com/api/character/avatar/2.jpeg"
                                ),

                            )
                        )

                    )

                }
            }
        }
    }
}

