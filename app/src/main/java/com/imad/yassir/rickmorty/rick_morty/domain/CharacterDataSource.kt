package com.imad.yassir.rickmorty.rick_morty.domain


import com.imad.yassir.rickmorty.core.domain.util.NetworkError
import com.imad.yassir.rickmorty.core.domain.util.Result

interface CharacterDataSource {
    suspend fun getCharactersList(): Result<List<Character>,NetworkError>
}