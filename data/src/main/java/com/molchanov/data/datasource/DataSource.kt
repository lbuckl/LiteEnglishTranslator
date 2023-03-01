package com.molchanov.data.datasource

/**
 * Основной интерфейс получения данных
 */
interface DataSource<T> {
    suspend fun getData(word: String): T
}
