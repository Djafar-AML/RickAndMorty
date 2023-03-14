package com.example.rickandmorty.hilt

import com.example.rickandmorty.network.RickAndMortyApi
import com.example.rickandmorty.ui.activities.arch.SharedRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun provideRepo(rickAndMortyApi: RickAndMortyApi): SharedRepo = SharedRepo(rickAndMortyApi)

}