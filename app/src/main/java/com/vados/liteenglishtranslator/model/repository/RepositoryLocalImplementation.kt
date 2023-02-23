package com.vados.liteenglishtranslator.model.repository

import com.vados.liteenglishtranslator.model.datasource.local.DataSourceLocal
import com.vados.liteenglishtranslator.model.domain.DataModel

class RepositoryLocalImplementation (private val dataSource: DataSourceLocal) :
    RepositoryLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveDataToDB(translations: DataModel) {
        dataSource.saveDataToDB(translations)
    }
}