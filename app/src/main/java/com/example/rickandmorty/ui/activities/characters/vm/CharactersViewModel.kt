package com.example.rickandmorty.ui.activities.characters.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.rickandmorty.ui.activities.characters.datasource.CharactersDataSourceFactory
import com.example.rickandmorty.network.response.GetCharacterByIdResponse
import com.example.rickandmorty.util.PAGE_SIZE
import com.example.rickandmorty.util.PREFETCH_DISTANCE
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    dataSourceFactory: CharactersDataSourceFactory
) : ViewModel() {

    private val pageListConfig = PagedList.Config.Builder()
        .setPageSize(PAGE_SIZE)
        .setPrefetchDistance(PREFETCH_DISTANCE)
        .build()

    val charactersPagedListLiveData: LiveData<PagedList<GetCharacterByIdResponse>> =
        LivePagedListBuilder(dataSourceFactory, pageListConfig).build()

}