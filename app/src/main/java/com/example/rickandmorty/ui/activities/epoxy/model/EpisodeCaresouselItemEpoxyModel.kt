package com.example.rickandmorty.ui.activities.epoxy.model

import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ModelEpisodeCarouselItemBinding
import com.example.rickandmorty.domain.models.Episode
import com.example.rickandmorty.epxoybinding.ViewBindingKotlinModel

data class EpisodeCaresouselItemEpoxyModel(
    private val episode: Episode
) : ViewBindingKotlinModel<ModelEpisodeCarouselItemBinding>(R.layout.model_episode_carousel_item) {

    override fun ModelEpisodeCarouselItemBinding.bind() {
        episodeTextView.text = episode.episode
        episodeDetailsTextView.text = "${episode.name}\n${episode.airDate}"
    }

}
