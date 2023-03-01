package com.vados.liteenglishtranslator.model.datasource.local

import com.molchanov.domain.model.domain.DataModel
import com.vados.liteenglishtranslator.model.datasource.DataSource

/**
 * Класс для реализации записи и получения данных из кеша
 */
class DataSourceLocal(private val remoteProvider: RoomDataBaseImplementation) :
    DataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> = remoteProvider.getData(word)

    fun saveDataToDB(translations: DataModel) {
        remoteProvider.setData(translations)
    }
}
