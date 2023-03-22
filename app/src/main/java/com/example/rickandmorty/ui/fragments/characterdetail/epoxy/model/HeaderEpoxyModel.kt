package com.example.rickandmorty.ui.fragments.characterdetail.epoxy.model

import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ModelCharacterDetailsHeaderBinding
import com.example.rickandmorty.epxoybinding.ViewBindingKotlinModel

data class HeaderEpoxyModel(
    private val name: String,
    private val gender: String,
    private val status: String
) : ViewBindingKotlinModel<ModelCharacterDetailsHeaderBinding>(R.layout.model_character_details_header) {

    override fun ModelCharacterDetailsHeaderBinding.bind() {

        nameTextView.text = name
        aliveTextView.text = status
        genderImageView.setImageResource(genderImageType(gender))
    }

    private fun genderImageType(gender: String): Int {

        return if (gender.equals("male", true)) {
            R.drawable.ic_male_24
        } else {
            R.drawable.ic_female_24
        }
    }

}
