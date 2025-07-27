package com.imad.yassir.rickmorty.core.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.imad.yassir.rickmorty.core.presentation.util.ObserveAsEvents
import com.imad.yassir.rickmorty.rick_morty.presentation.character_list.CharacterListEvent
import com.imad.yassir.rickmorty.rick_morty.presentation.character_list.CharacterListScreen
import com.imad.yassir.rickmorty.rick_morty.presentation.character_list.CharacterListViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun AdaptiveNavigation(modifier: Modifier = Modifier,viewModel: CharacterListViewModel= koinViewModel()) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    ObserveAsEvents(events= viewModel.events){
        event -> when(event){
            is CharacterListEvent.ShowError->{
                Toast.makeText(
                    context,
                    event.message.toString(),
                    Toast.LENGTH_LONG
                ).show()
            }

        is CharacterListEvent.NavigateToCharacterDetail -> TODO()
        is CharacterListEvent.ShowToast -> TODO()
    }
    }
    CharacterListScreen(
        state = state,
        onAction = viewModel::onAction,
        modifier = modifier
    )

}