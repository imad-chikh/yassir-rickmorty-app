package com.imad.yassir.rickmorty.rick_morty.presentation.character_details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.imad.yassir.rickmorty.R
import com.imad.yassir.rickmorty.rick_morty.presentation.models.CharacterUi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import org.koin.androidx.compose.koinViewModel

val DetailCardBackground = Color(0xFF1A1A1A)
val StatusAliveColor = Color(0xFF4CAF50)
val StatusDeadColor = Color(0xFFFF5252)
val StatusUnknownColor = Color(0xFFFFEB3B)
val SpeciesIconColor = Color(0xFF4CAF50)
val TextPrimaryWhite = Color(0xFFFFFFFF)
val TextSecondaryGrey = Color(0xFFB0BEC5)
val IdTextColor = Color(0xFF757575)

@Composable
fun InfoCard(
    icon: ImageVector,
    iconColor: Color,
    title: String,
    value: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = DetailCardBackground.copy(alpha = 0.8f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        iconColor.copy(alpha = 0.2f),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = iconColor,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Text Content
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondaryGrey
                )
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    color = TextPrimaryWhite
                )
            }
        }
    }
}

@Composable
fun ExpandableInfoCard(
    icon: ImageVector,
    iconColor: Color,
    title: String,
    value: String,
    expandedContent: @Composable () -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded },
        colors = CardDefaults.cardColors(
            containerColor = DetailCardBackground.copy(alpha = 0.8f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Icon
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            iconColor.copy(alpha = 0.2f),
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        tint = iconColor,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Text Content
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondaryGrey
                    )
                    Text(
                        text = value,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Medium
                        ),
                        color = TextPrimaryWhite
                    )
                }

                // Expand/Collapse Icon
                Icon(
                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = if (isExpanded) "Collapse" else "Expand",
                    tint = TextSecondaryGrey,
                    modifier = Modifier.size(24.dp)
                )
            }

            // Expanded Content
            if (isExpanded) {
                Spacer(modifier = Modifier.height(12.dp))
                expandedContent()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: CharacterDetailsViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    
    val gradientBackground = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF0A0A0A),
            Color(0xFF1A1A1A),
            Color(0xFF0A0A0A)
        )
    )
    val backgroundImagePainter =
        painterResource(id = R.drawable.background)

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = backgroundImagePainter,
                contentScale = ContentScale.FillBounds
            ),
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack,
                        modifier = Modifier
                            .padding(8.dp)
                            .background(
                                Color.Black.copy(alpha = 0.5f),
                                CircleShape
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        containerColor = Color.Transparent,
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            when {
                state.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = Color.White
                        )
                    }
                }
                state.error != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = state.error ?: "Unknown error",
                                color = Color.White,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(
                                onClick = { viewModel.retry() }
                            ) {
                                Text("Retry")
                            }
                        }
                    }
                }
                state.characterDetails != null -> {
                    val character = state.characterDetails!!
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(paddingValues)
                    ) {
                        // Character Image Section
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(400.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            AsyncImage(
                                model = character.image,
                                contentDescription = "characterImage",
                                modifier = Modifier
                                    .size(300.dp)
                                    .clip(RoundedCornerShape(20.dp)),
                                contentScale = ContentScale.Crop
                            )
                        }

                Spacer(modifier = Modifier.height(16.dp))

                // Character Info Section
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                ) {
                    // Character Name
                    Text(
                        text = character.name,
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp
                        ),
                        color = TextPrimaryWhite
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Status and Gender Row
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Status Indicator
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .background(
                                    color = when (character.status.lowercase()) {
                                        "alive" -> StatusAliveColor
                                        "dead" -> StatusDeadColor
                                        else -> StatusUnknownColor
                                    },
                                    shape = CircleShape
                                )
                        )

                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = character.status,
                            style = MaterialTheme.typography.bodyLarge,
                            color = when (character.status.lowercase()) {
                                "alive" -> StatusAliveColor
                                "dead" -> StatusDeadColor
                                else -> StatusUnknownColor
                            },
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        // Gender
                        Text(
                            text = "• ${character.gender}",
                            style = MaterialTheme.typography.bodyLarge,
                            color = TextSecondaryGrey
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // ID Section
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "ID",
                            style = MaterialTheme.typography.bodyMedium,
                            color = IdTextColor
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "#${character.id}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = IdTextColor
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Information Cards
                    InfoCard(
                        icon = Icons.Default.Science,
                        iconColor = SpeciesIconColor,
                        title = "Species",
                        value = character.species
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Type (if not empty)
                    if (character.type.isNotEmpty()) {
                        InfoCard(
                            icon = Icons.Default.Category,
                            iconColor = Color(0xFF2196F3),
                            title = "Type",
                            value = character.type
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }

                    // Origin
                    InfoCard(
                        icon = Icons.Default.LocationOn,
                        iconColor = Color(0xFFFF9800),
                        title = "Origin",
                        value = character.origin.name
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Current Location
                    InfoCard(
                        icon = Icons.Default.LocationOn,
                        iconColor = Color(0xFF9C27B0),
                        title = "Current Location",
                        value = character.location.name
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Episode Count
                    ExpandableInfoCard(
                        icon = Icons.Default.PlayArrow,
                        iconColor = Color(0xFFE91E63),
                        title = "Episodes",
                        value = "${character.episode.size} episodes"
                    ) {
                        // Expanded content showing all episodes
                        Column {
                            Text(
                                text = "Episode List:",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.SemiBold
                                ),
                                color = TextSecondaryGrey,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )
                            character.episode.forEachIndexed { index, episodeUrl ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(0xFF2A2A2A).copy(alpha = 0.6f)
                                    ),
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(12.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        // Episode number badge
                                        Box(
                                            modifier = Modifier
                                                .size(28.dp)
                                                .background(
                                                    Color(0xFFE91E63).copy(alpha = 0.8f),
                                                    CircleShape
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = "${index + 1}",
                                                style = MaterialTheme.typography.bodySmall.copy(
                                                    fontWeight = FontWeight.Bold
                                                ),
                                                color = Color.White
                                            )
                                        }
                                        
                                        Spacer(modifier = Modifier.width(12.dp))
                                        
                                        // Episode info
                                        Column(
                                            modifier = Modifier.weight(1f)
                                        ) {
                                            Text(
                                                text = "Episode ${index + 1}",
                                                style = MaterialTheme.typography.bodyMedium.copy(
                                                    fontWeight = FontWeight.Medium
                                                ),
                                                color = TextPrimaryWhite
                                            )
                                            Text(
                                                text = episodeUrl,
                                                style = MaterialTheme.typography.bodySmall,
                                                color = TextSecondaryGrey,
                                                modifier = Modifier.padding(top = 2.dp)
                                            )
                                        }
                                        
                                        // Play icon
                                        Icon(
                                            imageVector = Icons.Default.PlayArrow,
                                            contentDescription = "Episode ${index + 1}",
                                            tint = Color(0xFFE91E63),
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Created Date
                    val createdDate = try {
                        val date = java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", java.util.Locale.getDefault()).parse(character.created)
                        java.text.SimpleDateFormat("MMM dd, yyyy", java.util.Locale.getDefault()).format(date ?: java.util.Date())
                    } catch (e: Exception) {
                        character.created
                    }
                    
                    InfoCard(
                        icon = Icons.Default.Schedule,
                        iconColor = Color(0xFF607D8B),
                        title = "Created",
                        value = createdDate
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                }
            }
        }
    }
        }
    }}