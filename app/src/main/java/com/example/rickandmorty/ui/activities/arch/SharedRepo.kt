package com.example.rickandmorty.ui.activities.arch

import com.example.rickandmorty.domain.mapper.CharacterMapper
import com.example.rickandmorty.domain.models.Character
import com.example.rickandmorty.network.ApiClient

class SharedRepo constructor(
    private val apiClient: ApiClient
) {

    suspend fun characterById(characterId: Int): Character? {

        val response = apiClient.characterById(characterId)

        if (response.failed || response.isSuccessful.not()) {
            return null
        }

        return CharacterMapper.buildFrom(response.body)

    }

}