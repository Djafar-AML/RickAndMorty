package com.example.rickandmorty.ui.activities.characterdetail.epoxy.model

import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ModelCharacterDetailsImageBinding
import com.example.rickandmorty.epxoybinding.ViewBindingKotlinModel
import com.example.rickandmorty.extensions.loadByCoil

data class ImageEpoxyModel(
    private val imageUrl: String
) : ViewBindingKotlinModel<ModelCharacterDetailsImageBinding>(R.layout.model_character_details_image) {

    override fun ModelCharacterDetailsImageBinding.bind() {
        headerImageView.loadByCoil(imageUrl)
    }
}
