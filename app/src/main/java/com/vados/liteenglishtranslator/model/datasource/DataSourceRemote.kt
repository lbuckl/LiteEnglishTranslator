package com.vados.liteenglishtranslator.model.datasource

import com.vados.liteenglishtranslator.domain.DataModel
import io.reactivex.Observable

class DataSourceRemote(private val remoteProvider: RetrofitImplementation = RetrofitImplementation()) :
    DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}