package com.imad.yassir.rickmorty.core.data.networking

import com.imad.yassir.rickmorty.core.domain.utils.NetworkError
import io.ktor.client.call.NoTransformationFoundException
import com.imad.yassir.rickmorty.core.domain.utils.Result
import io.ktor.client.statement.HttpResponse
import io.ktor.client.call.body





suspend inline fun <reified T> responseToResult(
    response: HttpResponse
):Result<T, NetworkError>{
    return  when(response.status.value){
        in 200  until 300 -> {
            try {
                Result.Success(response.body<T>())
            }catch (e:NoTransformationFoundException){
                Result.Error(NetworkError.SERIALIZATION)

            }
        }
        408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
        429 -> Result.Error(NetworkError.TOO_MANY_REQUESTS)
        in 500 until 600 -> Result.Error(NetworkError.SERVER_ERROR)
        else -> Result.Error(NetworkError.UNKNOWN)

    }
}