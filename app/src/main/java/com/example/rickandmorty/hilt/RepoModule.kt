package com.example.rickandmorty.hilt

import com.example.rickandmorty.network.ApiClient
import com.example.rickandmorty.ui.activities.characterdetail.repo.SharedRepo
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
    fun provideRepo(apiClient: ApiClient): SharedRepo = SharedRepo(apiClient)

}