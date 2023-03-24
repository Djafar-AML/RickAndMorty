package com.example.rickandmorty.ui.fragments.characterdetail.repo

import com.example.rickandmorty.cache.CharacterDetailCache
import com.example.rickandmorty.domain.mapper.CharacterMapper
import com.example.rickandmorty.domain.models.Character
import com.example.rickandmorty.network.ApiClient
import com.example.rickandmorty.network.response.GetEpisodeByIdResponse

class SharedRepo constructor(
    private val apiClient: ApiClient
) {

    suspend fun characterById(characterId: Int): Character? {

        if (hasCharacterCached(characterId)) {
            return cachedCharacter(characterId)
        }

        val response = apiClient.characterById(characterId)


        if (response.failed || response.isSuccessful.not()) {
            return null
        }

        val networkEpisodes = episodesFormCharacterResponse(response.body.episodeList)

        val character = CharacterMapper.buildFrom(response.body, networkEpisodes)

        cacheCharacter(character)

        return character

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

    private fun hasCharacterCached(characterId: Int) =
        CharacterDetailCache.characterDetailMap.contains(characterId)

    private fun cachedCharacter(characterId: Int): Character? =
        CharacterDetailCache.characterDetailMap[characterId]

    private fun cacheCharacter(character: Character?) {
        character?.let {
            CharacterDetailCache.characterDetailMap[character.id] = character
        }
    }

}