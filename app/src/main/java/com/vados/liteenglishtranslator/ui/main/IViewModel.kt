package com.vados.liteenglishtranslator.ui.main

interface IViewModel {
    suspend fun getData(word: String, isOnline: Boolean)
}