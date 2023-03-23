package com.example.rickandmorty.ui.fragments.characterdetail.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.cache.CharacterDetailCache
import com.example.rickandmorty.domain.models.Character
import com.example.rickandmorty.extensions.asLiveData
import com.example.rickandmorty.ui.fragments.characterdetail.repo.SharedRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val sharedRepo: SharedRepo
) : ViewModel() {

    private val _characterByIdLiveData = MutableLiveData<Character>()
    val characterByIdLiveData = _characterByIdLiveData.asLiveData()

    fun refreshCharacter(characterId: Int) {

        if (hasCharacterCached(characterId)) {
            _characterByIdLiveData.postValue(cachedCharacter(characterId))
            return
        }

        viewModelScope.launch {

            val deferredResult = async(Dispatchers.IO) { sharedRepo.characterById(characterId) }

            _characterByIdLiveData.postValue(deferredResult.await())

            cacheCharacter(deferredResult.await())

        }

    }

    private fun hasCharacterCached(characterId: Int) =
        CharacterDetailCache.characterDetailMap.contains(characterId)

    private fun cachedCharacter(characterId: Int): Character? =
        CharacterDetailCache.characterDetailMap[characterId]

    private fun cacheCharacter(character: Character?) {
        character?.let {
            CharacterDetailCache.characterDetailMap[character.id] = character
        }
    }

}