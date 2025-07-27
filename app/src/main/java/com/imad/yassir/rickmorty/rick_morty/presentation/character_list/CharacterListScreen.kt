package com.imad.yassir.rickmorty.rick_morty.presentation.character_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imad.yassir.rickmorty.rick_morty.presentation.character_list.components.CharacterItem
import com.imad.yassir.rickmorty.rick_morty.presentation.models.CharacterUi

@Composable
fun CharacterListScreen(
    state: CharacterListState,
    onAction: (CharacterListAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    // Scroll detection for pagination (only when not searching)
    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo }
            .collect { visibleItems ->
                val totalItems = listState.layoutInfo.totalItemsCount
                val lastVisibleIndex = visibleItems.lastOrNull()?.index ?: 0
                if (totalItems > 0 &&
                    lastVisibleIndex >= totalItems - 3 && // Load when 3 items from end
                    state.canLoadMore &&
                    !state.isLoadingMore &&
                    !state.isSearchMode) {
                    onAction(CharacterListAction.LoadMoreCharacters)
                }
            }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 16.dp)
    ) {
        // Search TextField
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = { query -> onAction(CharacterListAction.SearchCharacters(query)) },
            placeholder = { Text("Search character...") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            },
            trailingIcon = {
                if (state.searchQuery.isNotEmpty()) {
                    IconButton(
                        onClick = { onAction(CharacterListAction.SearchCharacters("")) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear search"
                        )
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true,
        )

        // Content based on different states
        when {
            // Initial loading (when app starts)
            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            // Search mode - show search loading or results
            state.isSearchMode -> {
                if (state.isSearching) {
                    // Show loading when searching
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    // Show search results
                    LazyColumn(
                        state = listState,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        if (state.searchResults.isEmpty() && state.searchQuery.isNotEmpty()) {
                            // No search results found (only show when we actually searched)
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(32.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "No characters found for \"${state.searchQuery}\"",
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        } else {
                            // Show search results
                            items(state.searchResults) { character ->
                                CharacterItem(
                                    character = character,
                                    onClick = { onAction(CharacterListAction.OnCharacterClick(character.id)) }
                                )
                            }
                        }
                    }
                }
            }

            // Normal mode - show all characters with pagination
            else -> {
                LazyColumn(
                    state = listState,
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.characters) { character ->
                        CharacterItem(
                            character = character,
                            onClick = { onAction(CharacterListAction.OnCharacterClick(character.id)) }
                        )
                    }

                    // Load more indicator at the bottom (only in normal mode)
                    if (state.isLoadingMore) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }
        }
    }
}