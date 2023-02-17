package com.vados.liteenglishtranslator.model.datasource

import kotlinx.coroutines.Deferred

/**
 * Основной интерфейс получения данных
 */
interface DataSource<T> {
    suspend fun getData(word: String): T
}
