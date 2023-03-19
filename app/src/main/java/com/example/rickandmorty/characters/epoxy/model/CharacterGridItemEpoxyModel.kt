package com.example.rickandmorty.characters.epoxy.model

import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ModelCharacterListItemBinding
import com.example.rickandmorty.epxoybinding.ViewBindingKotlinModel
import com.example.rickandmorty.extensions.loadByCoil

data class CharacterGridItemEpoxyModel(
    private val imageUrl: String,
    private val name: String
) : ViewBindingKotlinModel<ModelCharacterListItemBinding>(R.layout.model_character_list_item) {

    override fun ModelCharacterListItemBinding.bind() {
        characterImageView.loadByCoil(imageUrl)
        characterNameTextView.text = name
    }
}
