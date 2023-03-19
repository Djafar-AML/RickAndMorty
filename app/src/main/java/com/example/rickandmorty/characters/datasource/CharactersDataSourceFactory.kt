package com.example.rickandmorty.characters.datasource

import androidx.paging.DataSource
import com.example.rickandmorty.characters.repo.CharactersRepo
import com.example.rickandmorty.network.response.GetCharacterByIdResponse
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class CharactersDataSourceFactory @Inject constructor(
    private val coroutineScope: CoroutineScope,
    private val repo: CharactersRepo
) : DataSource.Factory<Int, GetCharacterByIdResponse>() {

    override fun create(): DataSource<Int, GetCharacterByIdResponse> {

        return CharactersDataSource(
            coroutineScope, repo
        )
    }
}