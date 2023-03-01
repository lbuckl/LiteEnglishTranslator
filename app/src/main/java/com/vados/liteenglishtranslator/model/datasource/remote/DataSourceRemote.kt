package com.vados.liteenglishtranslator.model.datasource.remote

import com.vados.liteenglishtranslator.model.datasource.DataSource
import com.molchanov.domain.model.domain.DataModel

/**
 * Класс для реализации получения данных удалённо от Api
 */
class DataSourceRemote(private val remoteProvider: RetrofitImplementation) :
    DataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> = remoteProvider.getData(word)
}
