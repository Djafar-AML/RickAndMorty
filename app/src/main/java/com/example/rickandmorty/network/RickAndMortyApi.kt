package com.example.rickandmorty.network

import com.example.rickandmorty.network.response.GetCharacterByIdResponse
import com.example.rickandmorty.network.response.GetCharactersPageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character/{character-id}")
    suspend fun characterById(
        @Path("character-id") characterId: Int
    ): Response<GetCharacterByIdResponse>

    @GET("character")
    suspend fun characterList(
        @Query("page")
        pageIndex: Int
    ): Response<GetCharactersPageResponse>

}