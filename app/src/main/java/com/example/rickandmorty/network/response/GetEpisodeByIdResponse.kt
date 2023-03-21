package com.example.rickandmorty.network.response

import com.squareup.moshi.Json

data class GetEpisodeByIdResponse(
    @Json(name = "air_date")
    val airDate: String,
    val characterList: List<String> = listOf(),
    val created: String = "",
    val episode: String = "",
    val id: Int = 0,
    val name: String = "",
    val url: String = ""
)