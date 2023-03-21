package com.example.rickandmorty.network

import com.example.rickandmorty.network.response.GetCharacterByIdResponse
import com.example.rickandmorty.network.response.GetCharactersPageResponse
import com.example.rickandmorty.network.response.GetEpisodeByIdResponse
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

    @GET("episode/{episode-id}")
    suspend fun episodeById(
        @Path("episode-id")
        episodeId: Int
    ): Response<GetEpisodeByIdResponse>

    @GET("episode/{episode-range}")
    suspend fun episodeRange(
        @Path("episode-range")
        episodeRange: String
    ): Response<List<GetEpisodeByIdResponse>>

}