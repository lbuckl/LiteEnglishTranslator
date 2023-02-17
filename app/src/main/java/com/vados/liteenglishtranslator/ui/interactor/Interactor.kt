package com.vados.liteenglishtranslator.ui.interactor

/**
 * Интерфейс интерактора для получения данных
 */
interface Interactor<T> {

    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}