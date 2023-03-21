package com.example.rickandmorty.network

import com.example.rickandmorty.network.response.GetCharacterByIdResponse
import com.example.rickandmorty.network.response.GetCharactersPageResponse
import com.example.rickandmorty.network.response.GetEpisodeByIdResponse
import retrofit2.Response
import javax.inject.Inject

class ApiClient @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi
) {

    suspend fun characterById(characterId: Int): SimpleResponse<GetCharacterByIdResponse> {
        return safeApiCall { rickAndMortyApi.characterById(characterId) }
    }

    suspend fun characterListPage(pageIndex: Int): SimpleResponse<GetCharactersPageResponse> {
        return safeApiCall { rickAndMortyApi.characterList(pageIndex) }
    }

    suspend fun episodeById(episodeId: Int): SimpleResponse<GetEpisodeByIdResponse> {
        return safeApiCall { rickAndMortyApi.episodeById(episodeId) }
    }

    suspend fun episodeRage(episodeRange: String): SimpleResponse<List<GetEpisodeByIdResponse>> {
        return safeApiCall { rickAndMortyApi.episodeRange(episodeRange) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {

        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }

    }

}