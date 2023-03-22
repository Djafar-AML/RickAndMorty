package com.example.rickandmorty.ui.activities.characterdetail.epoxy.model

import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ModelTitleBinding
import com.example.rickandmorty.epxoybinding.ViewBindingKotlinModel

data class TitleEpoxyModel(
    private val title: String
) : ViewBindingKotlinModel<ModelTitleBinding>(R.layout.model_title) {

    override fun ModelTitleBinding.bind() {
        titleTextView.text = title
    }

}
