package com.molchanov.data.repository

/**
 * Интерфейс основного репозитория
 */
interface RepositoryRemote<T> {

    suspend fun getData(word: String): T
}