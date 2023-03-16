package com.example.rickandmorty.network

import com.example.rickandmorty.GetCharacterByIdResponse
import retrofit2.Response
import javax.inject.Inject

class ApiClient @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi
) {

    suspend fun characterById(characterId: Int): SimpleResponse<GetCharacterByIdResponse> {
        return safeApiCall { rickAndMortyApi.characterById(characterId) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {

        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }

    }

}