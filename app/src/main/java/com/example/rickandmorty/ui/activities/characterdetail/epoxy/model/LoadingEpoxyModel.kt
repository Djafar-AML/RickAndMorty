package com.example.rickandmorty.ui.activities.characterdetail.epoxy.model

import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ModelLoadingBinding
import com.example.rickandmorty.epxoybinding.ViewBindingKotlinModel

class LoadingEpoxyModel : ViewBindingKotlinModel<ModelLoadingBinding>(R.layout.model_loading) {

    override fun ModelLoadingBinding.bind() {
        // nothing to do
    }

    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }

}