package com.example.rickandmorty.ui.activities.characters.epoxy.model

import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ModelCharacterListTitleBinding
import com.example.rickandmorty.epxoybinding.ViewBindingKotlinModel

data class CharacterListTitleEpoxyModel(
    private val title: String
) : ViewBindingKotlinModel<ModelCharacterListTitleBinding>(R.layout.model_character_list_title) {

    override fun ModelCharacterListTitleBinding.bind() {

        titleTextView.text = title
    }

    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }
}
