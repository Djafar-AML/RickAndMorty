package com.example.rickandmorty.ui.activities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.GetCharacterByIdResponse
import com.example.rickandmorty.network.RickAndMortyApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi
) : ViewModel() {

    var data: MutableLiveData<GetCharacterByIdResponse?> = MutableLiveData(null)

    init {
        viewModelScope.launch {

            val deferredResult = async(Dispatchers.IO) {
                rickAndMortyApi.getData(1)
            }

            data?.postValue(deferredResult.await())
            println("data is here: $data")

        }
    }

}