package com.example.rickandmorty.domain.mapper

import com.example.rickandmorty.domain.models.Episode
import com.example.rickandmorty.network.response.GetEpisodeByIdResponse

object EpisodeMapper {

    fun buildFrom(networkEpisode: GetEpisodeByIdResponse): Episode {
        return Episode(
            id = networkEpisode.id,
            name = networkEpisode.name,
            airDate = networkEpisode.airDate,
            episode = networkEpisode.episode
        )
    }

}