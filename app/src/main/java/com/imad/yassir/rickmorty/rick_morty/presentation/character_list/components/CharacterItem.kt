package com.imad.yassir.rickmorty.rick_morty.presentation.character_list.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.imad.yassir.rickmorty.R
import com.imad.yassir.rickmorty.rick_morty.presentation.models.CharacterUi

val CardCyanGlow = Color(0xFF00E5FF)
val CardCyanDark = Color(0xFF00ACC1)
val CardBackground = Color(0xFF17517e)
val CardSurface = Color(0xFF17517e)
val StatusGreen = Color(0xFF4CAF50)
val StatusRed = Color(0xFFFF5252)
val StatusYellow = Color(0xFFFFEB3B)
val TextPrimary = Color(0xFFFFFFFF)
val TextSecondary = Color(0xFFB0BEC5)
val IconTint = Color(0xFF00E5FF)

@Composable
fun CharacterItem(character: CharacterUi, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Card(
            modifier = Modifier
                .border(
                    width = 2.dp,
                    brush = Brush.linearGradient(
                        colors = listOf(
                            CardCyanGlow,
                            CardCyanDark,
                            CardCyanGlow
                        )
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                .shadow(
                    elevation = 12.dp,
                    spotColor = CardCyanGlow,
                    ambientColor = CardCyanGlow,
                    shape = RoundedCornerShape(12.dp)
                ),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(0.dp),
            colors = CardDefaults.cardColors(
                containerColor = CardBackground
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CardBackground)
            ) {
                // Character Image with rounded corners
                val imageRequest = ImageRequest.Builder(LocalContext.current)
                    .data(character.imageUrl)
                    .crossfade(true)
                    .build()

                Image(
                    painter = rememberAsyncImagePainter(imageRequest),
                    contentDescription = character.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(200.dp)
                        .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                )

                // Content area with dark background
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(CardSurface)
                        .padding(12.dp)
                ) {
                    Column {
                        // Name & Species
                        Text(
                            text = character.name,
                            style = MaterialTheme.typography.titleMedium,
                            color = TextPrimary,
                            modifier = Modifier.fillMaxWidth(),
                        )
                        Text(
                            text = character.species,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                textDecoration = if (character.status == "Dead") TextDecoration.LineThrough else TextDecoration.None
                            ),

                            color = TextSecondary,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.size(8.dp))

                        // Status & User icons row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Status indicator (heart icon with status color)
                            val statusColor = when(character.status?.lowercase()) {
                                "alive" -> StatusGreen
                                "dead" -> StatusRed
                                else -> StatusYellow
                            }

                            Icon(
                                imageVector = if(character.status?.lowercase() == "dead")
                                    Icons.Default.HeartBroken else Icons.Default.Favorite,
                                contentDescription = "Status: ${character.status}",
                                tint = statusColor,
                                modifier = Modifier.size(20.dp)
                            )

                            Spacer(modifier = Modifier.width(8.dp))


                        }
                    }
                }
            }
        }
    }
}