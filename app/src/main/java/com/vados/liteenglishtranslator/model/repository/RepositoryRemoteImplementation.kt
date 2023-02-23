package com.vados.liteenglishtranslator.model.repository

import com.vados.liteenglishtranslator.model.datasource.DataSource
import com.vados.liteenglishtranslator.model.domain.DataModel

/**
 * Класс основного репозитория для получения данных
 * посылаем слово для перевода [word]
 * получаем ответ в виде списка элементов [DataModel]
 */
class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}
