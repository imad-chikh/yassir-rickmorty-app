package com.imad.yassir.rickmorty.rick_morty.presentation.character_details

import androidx.compose.foundation.background
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

val DetailCardBackground = Color(0xFF1A1A1A)
val StatusAliveColor = Color(0xFF4CAF50)
val StatusDeadColor = Color(0xFFFF5252)
val StatusUnknownColor = Color(0xFFFFEB3B)
val SpeciesIconColor = Color(0xFF4CAF50)
val TextPrimaryWhite = Color(0xFFFFFFFF)
val TextSecondaryGrey = Color(0xFFB0BEC5)
val IdTextColor = Color(0xFF757575)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    characterId: Int,
    characterName: String,
    characterImage: String,
    characterStatus: String,
    characterSpecies: String,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier


) {
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
modifier= Modifier.fillMaxSize().    paint(
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
                        model = characterImage,
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
                        text = characterName,
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
                                    color = when (characterStatus.lowercase()) {
                                        "alive" -> StatusAliveColor
                                        "dead" -> StatusDeadColor
                                        else -> StatusUnknownColor
                                    },
                                    shape = CircleShape
                                )
                        )

                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "$characterStatus",

                            style = MaterialTheme.typography.bodyLarge,
                            color  = when (characterStatus.lowercase()) {
                                "alive" -> StatusAliveColor
                                "dead" -> StatusDeadColor
                                else -> StatusUnknownColor
                            },
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
                            text = "#${characterId}",
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
                        value = characterSpecies
                    )

                    Spacer(modifier = Modifier.height(12.dp))


                }
            }
        }
    }
}

@Composable
private fun InfoCard(
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