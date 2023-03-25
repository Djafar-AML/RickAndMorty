package com.example.rickandmorty.ui.fragments.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.rickandmorty.databinding.FragmentCharacterListBinding
import com.example.rickandmorty.ui.fragments.characters.epoxy.controller.CharacterListPagingEpoxyController
import com.example.rickandmorty.ui.fragments.characters.vm.CharactersViewModel
import com.example.rickandmorty.ui.fragments.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment : BaseFragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding by lazy { _binding!! }
    private val epoxyController by lazy { CharacterListPagingEpoxyController(::onItemClickCallback) }
    private val charactersViewModel: CharactersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setupObservers()
    }

    private fun initViews() {
        binding.charactersEpoxy.setController(epoxyController)
    }

    private fun setupObservers() {

        charactersViewModel.charactersPagedListLiveData.observe(viewLifecycleOwner) { pagedList ->
            epoxyController.submitList(pagedList)
        }

    }

    private fun onItemClickCallback(itemId: Int) {

        val direction = CharacterListFragmentDirections.toCharacterDetailFragment(itemId)
        navigateViaNavGraph(direction)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}