package com.imad.yassir.rickmorty.rick_morty.domain


import com.imad.yassir.rickmorty.core.domain.util.NetworkError
import com.imad.yassir.rickmorty.core.domain.util.Result
import com.imad.yassir.rickmorty.rick_morty.data.networking.dto.CharacterResponseDto

interface CharacterDataSource {
    suspend fun getCharacters(): Result<List<Character>,NetworkError>
}