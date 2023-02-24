package com.vados.liteenglishtranslator.model.repository

/**
 * Интерфейс основного репозитория
 */
interface RepositoryRemote<T> {

    suspend fun getData(word: String): T
}