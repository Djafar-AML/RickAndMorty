package com.example.rickandmorty.characters.epoxy.model

import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ModelCharacterListItemBinding
import com.example.rickandmorty.epxoybinding.ViewBindingKotlinModel
import com.example.rickandmorty.extensions.loadByCoil

data class CharacterGridItemEpoxyModel(
    private val itemId: Int,
    private val imageUrl: String,
    val name: String,
    val onItemClickCallback: (Int) -> Unit,

    ) : ViewBindingKotlinModel<ModelCharacterListItemBinding>(R.layout.model_character_list_item) {

    override fun ModelCharacterListItemBinding.bind() {
        characterImageView.loadByCoil(imageUrl)
        characterNameTextView.text = name

        root.setOnClickListener {
            onItemClickCallback(itemId)
        }
    }
}
