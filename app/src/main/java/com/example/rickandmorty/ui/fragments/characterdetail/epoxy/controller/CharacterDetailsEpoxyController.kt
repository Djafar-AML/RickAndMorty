package com.example.rickandmorty.ui.fragments.characterdetail.epoxy.controller

import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyController
import com.example.rickandmorty.domain.models.Character
import com.example.rickandmorty.ui.fragments.characterdetail.epoxy.model.*
import java.util.*

class CharacterDetailsEpoxyController : EpoxyController() {

    var isLoading = true
        set(value) {
            field = value
            if (value) {
                requestModelBuild()
            }
        }

    var characterResponse: Character? = null
        set(value) {
            field = value
            if (value != null) {
                isLoading = false
                requestModelBuild()
            }
        }

    override fun buildModels() {

        if (isLoading) {
            // show loading state
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        if (characterResponse == null) {
            // TODO: error state
            return
        }

        characterResponse?.let { character ->

            // header model
            HeaderEpoxyModel(
                name = character.name,
                gender = character.gender,
                status = character.status
            ).id("header ${UUID.randomUUID()}").addTo(this)

            // image model
            ImageEpoxyModel(
                imageUrl = character.image
            ).id("image ${UUID.randomUUID()}").addTo(this)

            // episode carousel list section
            if (character.episodeList.isNotEmpty()) {
                addTitleForCarousel(title = "Episodes")
                addEpisodeCarousel(character)
            }

            // data points model(s)
            DataPointEpoxyModel(
                title = "Origin",
                description = character.origin.name
            ).id("dataPoint ${UUID.randomUUID()}").addTo(this)

            DataPointEpoxyModel(
                title = "Species",
                description = character.species
            ).id("dataPoint ${UUID.randomUUID()}").addTo(this)

        }

    }

    private fun addTitleForCarousel(title: String) {
        TitleEpoxyModel(title)
            .id("title ${UUID.randomUUID()}")
            .addTo(this)
    }

    private fun addEpisodeCarousel(character: Character) {

        val items = episodeCarouselModels(character)
        CarouselModel_()
            .id("carousel ${UUID.randomUUID()}")
            .models(items)
            .numViewsToShowOnScreen(1.5f)
            .addTo(this)
    }

    private fun episodeCarouselModels(character: Character) =
        character.episodeList.map {
            EpisodeCaresouselItemEpoxyModel(it).id(it.id)
        }
}