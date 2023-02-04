package com.vados.liteenglishtranslator.model.datasource.remote

import com.vados.liteenglishtranslator.model.domain.DataModel
import com.vados.liteenglishtranslator.model.datasource.DataSource
import io.reactivex.Observable

/**
 * Класс для реализации получения данных удалённо от Api
 */
class DataSourceRemote(private val remoteProvider: RetrofitImplementation = RetrofitImplementation()) :
    DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}
