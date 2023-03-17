package com.example.rickandmorty.characters.datasource

import androidx.paging.PageKeyedDataSource
import com.example.rickandmorty.characters.repo.CharactersRepo
import com.example.rickandmorty.network.response.GetCharacterByIdResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersDataSource @Inject constructor(
    private val coroutineScope: CoroutineScope,
    private val repo: CharactersRepo
) : PageKeyedDataSource<Int, GetCharacterByIdResponse>() {


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, GetCharacterByIdResponse>
    ) {

        coroutineScope.launch {
            val characterList = repo.characterList(1)
            callback.onResult(characterList, null, 2)
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, GetCharacterByIdResponse>
    ) {
        coroutineScope.launch {
            val characterList = repo.characterList(params.key)
            callback.onResult(characterList, params.key + 1)
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, GetCharacterByIdResponse>
    ) {
        // nothing to do
    }
}