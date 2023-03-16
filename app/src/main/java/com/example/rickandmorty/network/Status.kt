package com.example.rickandmorty.network

sealed class Status {
    object Success : Status()
    object Failure : Status()
}