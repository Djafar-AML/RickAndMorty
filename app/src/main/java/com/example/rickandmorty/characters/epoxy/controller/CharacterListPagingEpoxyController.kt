package com.example.rickandmorty.characters.epoxy.controller

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.example.rickandmorty.characters.epoxy.model.CharacterGridItemEpoxyModel
import com.example.rickandmorty.characters.epoxy.model.CharacterListTitleEpoxyModel
import com.example.rickandmorty.network.response.GetCharacterByIdResponse
import com.example.rickandmorty.ui.activities.epoxy.model.LoadingEpoxyModel
import java.util.*

class CharacterListPagingEpoxyController : PagedListEpoxyController<GetCharacterByIdResponse>() {

    override fun buildItemModel(
        currentPosition: Int,
        item: GetCharacterByIdResponse?
    ): EpoxyModel<*> {

        return CharacterGridItemEpoxyModel(item!!.image, item.name).id(item.id)
    }

    override fun addModels(models: List<EpoxyModel<*>>) {

        if (models.isEmpty()) {
            showLoading()
            return
        }

        addMainFamilyHeader()

        val mainFamilyList = mainFamilyList(models)

        super.addModels(mainFamilyList)

        val groupedByFirstCharacter = groupByFirstCharacter(models)

        showHeadersAndContents(groupedByFirstCharacter)

    }


    private fun showLoading() {

        LoadingEpoxyModel().id("loading").addTo(this)
    }

    private fun addMainFamilyHeader() {

        CharacterListTitleEpoxyModel("Main Family")
            .id("main_family_header")
            .addTo(this)
    }

    private fun mainFamilyList(models: List<EpoxyModel<*>>) =
        models.subList(0, 5)


    private fun groupByFirstCharacter(models: List<EpoxyModel<*>>): Map<Char, List<CharacterGridItemEpoxyModel>> {

        return (models.subList(5, models.size) as List<CharacterGridItemEpoxyModel>).groupBy {
            it.name[0].uppercaseChar()
        }

    }

    private fun showHeadersAndContents(groupedByFirstCharacter: Map<Char, List<CharacterGridItemEpoxyModel>>) {

        groupedByFirstCharacter.forEach { mapEntry ->

            val uppercasedCharacter = uppercaseCharacter(mapEntry.key)

            addCharacterTitle(uppercasedCharacter)

            super.addModels(mapEntry.value)

        }
    }

    private fun uppercaseCharacter(character: Char) = character.toString().uppercase(Locale.US)

    private fun addCharacterTitle(uppercasedCharacter: String) {
        CharacterListTitleEpoxyModel(title = uppercasedCharacter)
            .id(uppercasedCharacter)
            .addTo(this)
    }


}