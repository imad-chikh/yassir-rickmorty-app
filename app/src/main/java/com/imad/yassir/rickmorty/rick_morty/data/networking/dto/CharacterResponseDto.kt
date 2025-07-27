package com.imad.yassir.rickmorty.rick_morty.data.networking.dto

import com.imad.yassir.rickmorty.rick_morty.domain.Character
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponseDto(
    val results: List<CharacterDto>
)
