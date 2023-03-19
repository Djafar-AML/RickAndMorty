package com.example.rickandmorty.characters

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmorty.characters.epoxy.controller.CharacterListPagingEpoxyController
import com.example.rickandmorty.characters.vm.CharactersViewModel
import com.example.rickandmorty.databinding.ActivityCharacterListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCharacterListBinding.inflate(layoutInflater) }
    private val epoxyController by lazy { CharacterListPagingEpoxyController() }

    private val charactersViewModel: CharactersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
        setupObservers()

    }

    private fun initViews() {
        binding.charactersEpoxy.setController(epoxyController)
    }

    private fun setupObservers() {

        charactersViewModel.charactersPagedListLiveData.observe(this) { pagedList ->
            epoxyController.submitList(pagedList)
        }

    }
}