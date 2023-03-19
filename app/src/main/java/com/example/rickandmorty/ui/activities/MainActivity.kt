package com.example.rickandmorty.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmorty.R
import com.example.rickandmorty.characters.vm.CharactersViewModel
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.ui.activities.arch.SharedViewModel
import com.example.rickandmorty.ui.activities.epoxy.controller.CharacterDetailsEpoxyController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val sharedViewModel: SharedViewModel by viewModels()

    private val charactersViewModel: CharactersViewModel by viewModels()

    private val epoxyController: CharacterDetailsEpoxyController by lazy { CharacterDetailsEpoxyController() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initEpoxyRecyclerView()
        setupObservers()

    }

    private fun setupObservers() {

        sharedViewModel.characterByIdLiveData.observe(this) { character ->
            character ?: return@observe
            epoxyController.characterResponse = character
        }

        charactersViewModel.charactersPagedListLiveData.observe(this) {
            println ("jafar ${it[0]}")
            println("hihhihihihi $it")
        }

    }

    private fun initEpoxyRecyclerView() {
        binding.epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
    }


}