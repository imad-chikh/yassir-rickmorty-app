package com.imad.yassir.rickmorty.rick_morty.presentation.models

import com.imad.yassir.rickmorty.rick_morty.domain.Character

data class CharacterUi(
    val id:Int,
val name: String,
val species: String,
val imageUrl: String

)
fun Character.toCharacterUi():CharacterUi{
    return CharacterUi(
            id = id,
            name = name,
            imageUrl = image,
            species = species,
    )
}