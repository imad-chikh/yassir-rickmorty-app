package com.imad.yassir.rickmorty.rick_morty.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    val id: Int,
    val name: String,
    val image: String,
    val species: String,
    val status: String,
)
