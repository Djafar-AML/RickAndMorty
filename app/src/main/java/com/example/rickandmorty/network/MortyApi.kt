package com.example.rickandmorty.network

import com.example.rickandmorty.GetCharacterByIdResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApi {

    @GET("character/{character-id}")
    suspend fun getData(
        @Path("character-id") characterId: Int
    ): GetCharacterByIdResponse

}