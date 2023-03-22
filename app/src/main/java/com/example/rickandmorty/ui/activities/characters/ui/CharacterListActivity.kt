package com.example.rickandmorty.ui.activities.characters.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmorty.databinding.ActivityCharacterListBinding
import com.example.rickandmorty.ui.activities.characters.epoxy.controller.CharacterListPagingEpoxyController
import com.example.rickandmorty.ui.activities.characters.vm.CharactersViewModel
import com.example.rickandmorty.ui.activities.characterdetail.ui.CharacterDetailActivity
import com.example.rickandmorty.util.CHARACTER_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCharacterListBinding.inflate(layoutInflater) }

    private val epoxyController by lazy { CharacterListPagingEpoxyController(::onItemClickCallback) }

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

    private fun onItemClickCallback(itemId: Int) {

        val intent = Intent(this, CharacterDetailActivity::class.java).apply {
            putExtra(CHARACTER_ID, itemId)
        }

        startActivity(intent)
    }

}