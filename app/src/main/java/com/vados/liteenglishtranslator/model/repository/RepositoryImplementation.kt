package com.vados.liteenglishtranslator.model.repository

import com.vados.liteenglishtranslator.model.datasource.DataSource
import com.vados.liteenglishtranslator.model.domain.DataModel
import io.reactivex.Observable

/**
 * Класс основного репозитория для получения данных
 * посылаем слово для перевода [word]
 * получаем ответ в виде списка элементов [DataModel]
 */
class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}
