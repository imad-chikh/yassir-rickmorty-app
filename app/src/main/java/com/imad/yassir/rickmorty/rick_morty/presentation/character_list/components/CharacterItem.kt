package com.imad.yassir.rickmorty.rick_morty.presentation.character_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.imad.yassir.rickmorty.R
import com.imad.yassir.rickmorty.rick_morty.presentation.models.CharacterUi

@Composable
fun CharacterItem(character: CharacterUi,onClick : ()->Unit) {
    Card (   modifier = Modifier.clickable ( onClick=onClick )
        .padding(horizontal = 16.dp, vertical = 8.dp)
        .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)){
        Row(
            modifier = Modifier.padding(16.dp)) {
            val imageRequest = ImageRequest.Builder(LocalContext.current)
                .data(character.imageUrl)
                .crossfade(true)
                .build()

            Image(
                painter = rememberAsyncImagePainter(imageRequest),
                contentDescription = character.name,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = character.name, style = MaterialTheme.typography.titleMedium)
                Text(text = character.species, style = MaterialTheme.typography.bodySmall)
            }
        
    }
    
}}

