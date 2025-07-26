package com.imad.yassir.rickmorty.core.data.networking


import com.imad.yassir.rickmorty.core.domain.utils.NetworkError
import com.imad.yassir.rickmorty.core.domain.utils.Result
import com.imad.yassir.rickmorty.core.data.networking.responseToResult

import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext


suspend inline fun <reified T> safeCAll(execute: () -> HttpResponse): Result<T, NetworkError> {
    val response = try {
        execute()
    } catch (e: UnresolvedAddressException) {
        return Result.Error(NetworkError.NO_INTERNET)
    } catch (e: SerializationException) {
        return Result.Error(NetworkError.SERIALIZATION)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        Result.Error(NetworkError.UNKNOWN)
    }

    return responseToResult(response)
}