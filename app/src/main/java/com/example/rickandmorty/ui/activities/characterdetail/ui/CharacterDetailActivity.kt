package com.example.rickandmorty.ui.activities.characterdetail.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmorty.databinding.ActivityCharacterDetailBinding
import com.example.rickandmorty.ui.activities.characterdetail.vm.SharedViewModel
import com.example.rickandmorty.ui.activities.characterdetail.epoxy.controller.CharacterDetailsEpoxyController
import com.example.rickandmorty.util.CHARACTER_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCharacterDetailBinding.inflate(layoutInflater) }

    private val sharedViewModel: SharedViewModel by viewModels()

    private val epoxyController: CharacterDetailsEpoxyController by lazy { CharacterDetailsEpoxyController() }

    private val itemId by lazy { parseItemIdFromIntent() }
    private fun parseItemIdFromIntent(): Int {
        return intent.getIntExtra(CHARACTER_ID, 1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        refreshCharacter()
        initEpoxyRecyclerView()
        setupObservers()

    }

    private fun refreshCharacter() {
        sharedViewModel.refreshCharacter(itemId)
    }

    private fun setupObservers() {

        sharedViewModel.characterByIdLiveData.observe(this) { character ->
            character ?: return@observe
            epoxyController.characterResponse = character
        }

    }

    private fun initEpoxyRecyclerView() {
        binding.epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
    }


}