package com.example.rickandmorty.network

import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("https://rickandmortyapi.com/api/location/3")
    suspend fun getData(): String

}