package com.imad.yassir.rickmorty.rick_morty.domain

data class CharacterDetails(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: LocationInfo,
    val location: LocationInfo,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

data class LocationInfo(
    val name: String,
    val url: String
)
