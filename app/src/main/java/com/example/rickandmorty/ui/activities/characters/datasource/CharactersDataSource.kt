package com.example.rickandmorty.ui.activities.characters.datasource

import androidx.paging.PageKeyedDataSource
import com.example.rickandmorty.ui.activities.characters.repo.CharactersRepo
import com.example.rickandmorty.network.response.GetCharacterByIdResponse
import com.example.rickandmorty.network.response.GetCharactersPageResponse
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

            val page: GetCharactersPageResponse? = repo.characterListPage(1)

            if (page == null) {
                callback.onResult(emptyList(), null, null)
                return@launch
            }

            callback.onResult(page.results, null, parsePageIndexFromNext(page.info.next))
        }

    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, GetCharacterByIdResponse>
    ) {

        coroutineScope.launch {

            val page = repo.characterListPage(params.key)

            if (page == null) {
                callback.onResult(emptyList(), null)
                return@launch
            }

            callback.onResult(page.results, parsePageIndexFromNext(page.info.next))

        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, GetCharacterByIdResponse>
    ) {
        // nothing to do
    }

    private fun parsePageIndexFromNext(next: String?): Int? {
        return next?.split("?page=")?.get(1)?.toInt()
    }

}