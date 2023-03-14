package com.example.rickandmorty.ui.activities.arch

import com.example.rickandmorty.GetCharacterByIdResponse
import com.example.rickandmorty.network.RickAndMortyApi

class SharedRepo constructor(
    private val rickAndMortyApi: RickAndMortyApi
) {

    suspend fun characterById(characterId: Int): GetCharacterByIdResponse? {

        val response = rickAndMortyApi.characterById(characterId)

        return if (response.isSuccessful)
            response.body()
        else
            null

    }

}