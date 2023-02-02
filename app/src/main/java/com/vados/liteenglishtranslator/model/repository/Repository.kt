package com.vados.liteenglishtranslator.model.repository

import io.reactivex.Observable

/**
 * Интерфейс основного репозитория
 */
interface Repository<T> {

    fun getData(word: String): Observable<T>
}