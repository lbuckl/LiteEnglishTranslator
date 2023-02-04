package com.vados.liteenglishtranslator.model.datasource.local

import com.vados.liteenglishtranslator.model.domain.DataModel
import com.vados.liteenglishtranslator.model.datasource.DataSource
import io.reactivex.Observable

/**
 * Класс для реализации получения данных из кеша
 */
class DataSourceLocal(private val remoteProvider: RoomDataBaseImplementation = RoomDataBaseImplementation()) :
    DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}
