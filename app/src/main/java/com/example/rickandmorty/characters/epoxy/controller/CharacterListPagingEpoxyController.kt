package com.example.rickandmorty.characters.epoxy.controller

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.example.rickandmorty.characters.epoxy.model.CharacterGridItemEpoxyModel
import com.example.rickandmorty.network.response.GetCharacterByIdResponse

class CharacterListPagingEpoxyController : PagedListEpoxyController<GetCharacterByIdResponse>() {

    override fun buildItemModel(
        currentPosition: Int,
        item: GetCharacterByIdResponse?
    ): EpoxyModel<*> {

        return CharacterGridItemEpoxyModel(item!!.image, item.name).id(item.id)

    }

}