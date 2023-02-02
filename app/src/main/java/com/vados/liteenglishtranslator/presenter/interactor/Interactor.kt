package com.vados.liteenglishtranslator.presenter.interactor

import io.reactivex.Observable

/**
 * Интерфейс интерактора для получения данных
 */
interface Interactor<T> {

    fun getData(word: String, fromRemoteSource: Boolean): Observable<T>
}