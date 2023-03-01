package com.molchanov.data.datasource.local

import com.molchanov.data.datasource.DataSource
import com.molchanov.domain.model.domain.DataModel

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
