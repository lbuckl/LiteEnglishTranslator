package com.vados.liteenglishtranslator.model.datasource.local

import com.vados.liteenglishtranslator.domain.DataModel
import com.vados.liteenglishtranslator.model.datasource.DataSource
import io.reactivex.Observable

class DataSourceLocal(private val remoteProvider: RoomDataBaseImplementation = RoomDataBaseImplementation()) :
    DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}
