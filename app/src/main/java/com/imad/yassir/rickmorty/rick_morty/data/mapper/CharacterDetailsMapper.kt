package com.imad.yassir.rickmorty.rick_morty.data.mapper


import com.imad.yassir.rickmorty.rick_morty.data.networking.dto.CharacterDetailsDto
import com.imad.yassir.rickmorty.rick_morty.domain.CharacterDetails

fun CharacterDetailsDto.toCharacterDetails(): CharacterDetails {
    return CharacterDetails(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = com.imad.yassir.rickmorty.rick_morty.domain.LocationInfo(origin.name, origin.url),
        location = com.imad.yassir.rickmorty.rick_morty.domain.LocationInfo(location.name, location.url),
        image = image,
        episode = episode,
        url = url,
        created = created
    )
}