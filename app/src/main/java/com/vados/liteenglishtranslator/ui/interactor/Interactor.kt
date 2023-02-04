package com.vados.liteenglishtranslator.ui.interactor

import io.reactivex.Observable

/**
 * Интерфейс интерактора для получения данных
 */
interface Interactor<T> {

    fun getData(word: String, fromRemoteSource: Boolean): Observable<T>
}