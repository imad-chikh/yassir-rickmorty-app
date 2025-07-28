package com.imad.yassir.rickmorty.rick_morty.domain


import android.app.DownloadManager.Query
import com.imad.yassir.rickmorty.core.domain.util.NetworkError
import com.imad.yassir.rickmorty.core.domain.util.Result
import com.imad.yassir.rickmorty.rick_morty.data.networking.dto.CharacterResponseDto

interface CharacterDataSource {
    suspend fun getCharacters(page: Int): Result<List<Character>, NetworkError>

    suspend fun searchCharacter(query: String): Result<List<Character>, NetworkError>

    suspend fun getCharacterDetails(id: Int): Result<CharacterDetails, NetworkError>
}