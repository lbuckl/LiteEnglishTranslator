package com.molchanov.data.repository

import com.molchanov.data.datasource.DataSource
import com.molchanov.domain.model.domain.DataModel

/**
 * Класс основного репозитория для получения данных
 * посылаем слово для перевода [word]
 * получаем ответ в виде списка элементов [DataModel]
 */
class RepositoryRemoteImplementation(private val dataSource: DataSource<List<DataModel>>) :
    RepositoryRemote<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}
