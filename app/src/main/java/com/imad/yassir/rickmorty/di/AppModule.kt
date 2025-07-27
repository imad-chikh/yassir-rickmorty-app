package com.imad.yassir.rickmorty.di


import com.imad.yassir.rickmorty.core.data.networking.HttpClientFactory
import com.imad.yassir.rickmorty.rick_morty.domain.CharacterDataSource
import com.imad.yassir.rickmorty.rick_morty.data.networking.RemoteCharacterDataSource
import com.imad.yassir.rickmorty.rick_morty.presentation.character_list.CharacterListViewModel

import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::RemoteCharacterDataSource).bind<CharacterDataSource>()

    viewModelOf(::CharacterListViewModel)
}