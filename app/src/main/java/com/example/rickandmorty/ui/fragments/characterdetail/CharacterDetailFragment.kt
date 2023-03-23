package com.example.rickandmorty.ui.fragments.characterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.example.rickandmorty.ui.fragments.base.BaseFragment
import com.example.rickandmorty.ui.fragments.characterdetail.epoxy.controller.CharacterDetailsEpoxyController
import com.example.rickandmorty.ui.fragments.characterdetail.vm.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment() {

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding by lazy { _binding!! }

    private val sharedViewModel: SharedViewModel by viewModels()

    private val epoxyController: CharacterDetailsEpoxyController by lazy { CharacterDetailsEpoxyController() }

    private val safeArgs: CharacterDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshCharacter()
        initEpoxyRecyclerView()
        setupObservers()
    }

    private fun refreshCharacter() {
        sharedViewModel.refreshCharacter(safeArgs.itemId)
    }

    private fun setupObservers() {

        sharedViewModel.characterByIdLiveData.observe(viewLifecycleOwner) { character ->
            character ?: return@observe
            epoxyController.characterResponse = character
        }

    }

    private fun initEpoxyRecyclerView() {
        binding.epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}