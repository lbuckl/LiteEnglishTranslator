package com.vados.liteenglishtranslator.model.repository

/**
 * Интерфейс основного репозитория
 */
interface Repository<T> {

    suspend fun getData(word: String): T
}