package com.example.rickandmorty.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

lateinit var application: RickAndMortyApplication
    private set

@HiltAndroidApp
class RickAndMortyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initApplication()
    }

    private fun initApplication() {
        application = this
    }

}