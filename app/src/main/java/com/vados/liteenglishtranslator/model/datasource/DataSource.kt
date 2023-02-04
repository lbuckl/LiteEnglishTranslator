package com.vados.liteenglishtranslator.model.datasource

import io.reactivex.Observable

/**
 * Основной интерфейс получения данных
 */
interface DataSource<T> {
    fun getData(word: String): Observable<T>
}
