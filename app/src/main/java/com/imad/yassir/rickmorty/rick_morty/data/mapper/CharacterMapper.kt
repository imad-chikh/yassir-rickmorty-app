package com.imad.yassir.rickmorty.rick_morty.data.mapper

import com.imad.yassir.rickmorty.rick_morty.data.networking.dto.CharacterDto
import com.imad.yassir.rickmorty.rick_morty.domain.Character


fun CharacterDto.toCharacter(): Character{
    return Character(
        id = id,
        name = name,
        image = image,
        species = species,
        status = status

    )
}