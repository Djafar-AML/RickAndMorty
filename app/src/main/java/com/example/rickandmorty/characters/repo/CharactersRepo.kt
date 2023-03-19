package com.example.rickandmorty.characters.repo

import com.example.rickandmorty.network.ApiClient
import com.example.rickandmorty.network.response.GetCharactersPageResponse
import javax.inject.Inject

class CharactersRepo @Inject constructor(
    private val apiClient: ApiClient
) {

    suspend fun characterListPage(pageIndex: Int): GetCharactersPageResponse? {

        val response = apiClient.characterListPage(pageIndex)

        if (response.failed || response.isSuccessful.not()) {
            return null
        }

        return response.body
    }
}