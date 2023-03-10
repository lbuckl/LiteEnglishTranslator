package com.molchanov.data.repository

import com.molchanov.data.datasource.local.DataSourceLocal
import com.molchanov.domain.model.domain.DataModel

class RepositoryLocalImplementation(private val dataSource: DataSourceLocal) :
    RepositoryLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveDataToDB(translations: DataModel) {
        if (dataSource.getData(translations.text!!).isNotEmpty())
            dataSource.saveDataToDB(translations)
    }
}