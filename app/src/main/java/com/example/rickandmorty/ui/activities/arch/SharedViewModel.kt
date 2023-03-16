package com.example.rickandmorty.ui.activities.arch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.network.response.GetCharacterByIdResponse
import com.example.rickandmorty.extensions.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val sharedRepo: SharedRepo
) : ViewModel() {

    private val _characterByIdLiveData = MutableLiveData<GetCharacterByIdResponse>()
    val characterByIdLiveData = _characterByIdLiveData.asLiveData()

    init {
        refreshCharacter(5)
    }
    fun refreshCharacter(characterId: Int) {

        viewModelScope.launch {

            val deferredResult = async(Dispatchers.IO) { sharedRepo.characterById(characterId) }

            _characterByIdLiveData.postValue(deferredResult.await())
            println("data is here: $_characterByIdLiveData")

        }
    }

}