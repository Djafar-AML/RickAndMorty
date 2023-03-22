package com.example.rickandmorty.ui.activities.characterdetail.repo

import com.example.rickandmorty.domain.mapper.CharacterMapper
import com.example.rickandmorty.domain.models.Character
import com.example.rickandmorty.network.ApiClient
import com.example.rickandmorty.network.response.GetEpisodeByIdResponse

class SharedRepo constructor(
    private val apiClient: ApiClient
) {

    suspend fun characterById(characterId: Int): Character? {

        val response = apiClient.characterById(characterId)

        if (response.failed || response.isSuccessful.not()) {
            return null
        }

        val networkEpisodes = episodesFormCharacterResponse(response.body.episodeList)

        return CharacterMapper.buildFrom(
            response.body,
            networkEpisodes
        )

    }

    private suspend fun episodesFormCharacterResponse(episodeList: List<String>): List<GetEpisodeByIdResponse> {

        val episodeRange = episodeRange(episodeList)

        val response = apiClient.episodeRage(episodeRange)

        if (response.failed || response.isSuccessful.not()) {
            return emptyList()
        }

        return response.body

    }

    private fun episodeRange(episode: List<String>): String {

        return episode.map {
            it.substring(it.lastIndexOf("/") + 1)
        }.toString()

    }

}