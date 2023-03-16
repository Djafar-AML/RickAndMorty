package com.example.rickandmorty.ui.activities.epoxy.model

import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ModelCharacterDetailsDataPointBinding
import com.example.rickandmorty.epxoybinding.ViewBindingKotlinModel

data class DataPointEpoxyModel(
     val title: String,
     val description: String
) : ViewBindingKotlinModel<ModelCharacterDetailsDataPointBinding>(R.layout.model_character_details_data_point) {

    override fun ModelCharacterDetailsDataPointBinding.bind() {
        labelTextView.text = title
        descriptionTextView.text = description
    }
}
