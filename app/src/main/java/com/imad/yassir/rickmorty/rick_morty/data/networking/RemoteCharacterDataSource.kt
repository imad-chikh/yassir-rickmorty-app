package com.imad.yassir.rickmorty.rick_morty.data.networking

import com.imad.yassir.rickmorty.core.data.networking.HttpClientFactory
import com.imad.yassir.rickmorty.core.data.networking.constructUrl
import com.imad.yassir.rickmorty.core.data.networking.safeCall
import com.imad.yassir.rickmorty.core.domain.util.NetworkError
import com.imad.yassir.rickmorty.core.domain.util.Result
import com.imad.yassir.rickmorty.core.domain.util.map
import com.imad.yassir.rickmorty.rick_morty.data.mapper.toCharacter
import com.imad.yassir.rickmorty.rick_morty.data.networking.dto.CharacterResponseDto
import com.imad.yassir.rickmorty.rick_morty.domain.Character
import com.imad.yassir.rickmorty.rick_morty.domain.CharacterDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteCharacterDataSource(private val httpClient: HttpClient): CharacterDataSource{

    //get the list of characters
    override suspend fun getCharactersList(): Result<List<Character>, NetworkError> {
        return safeCall<CharacterResponseDto> {
            httpClient.get(urlString = constructUrl("character")
            )}
            .map { response -> response.results.map { it.toCharacter()} }
        }


    }


