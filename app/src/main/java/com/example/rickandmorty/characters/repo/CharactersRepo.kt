package com.example.rickandmorty.characters.repo

import com.example.rickandmorty.network.ApiClient
import com.example.rickandmorty.network.response.GetCharacterByIdResponse
import javax.inject.Inject

class CharactersRepo @Inject constructor(
    apiClient: ApiClient
) {

    suspend fun characterList(pageIndex: Int): List<GetCharacterByIdResponse> {
        return emptyList()
    }
}