package com.example.rickandmorty.ui.activities.arch

import com.example.rickandmorty.GetCharacterByIdResponse
import com.example.rickandmorty.network.ApiClient

class SharedRepo constructor(
    private val apiClient: ApiClient
) {

    suspend fun characterById(characterId: Int): GetCharacterByIdResponse? {

        val response = apiClient.characterById(characterId)

        if (response.failed || response.isSuccessful.not()) {
            return null
        }

        return response.body

    }

}