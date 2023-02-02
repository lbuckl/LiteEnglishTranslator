package com.vados.liteenglishtranslator.model.repository

import com.vados.liteenglishtranslator.domain.DataModel
import com.vados.liteenglishtranslator.model.datasource.DataSource
import io.reactivex.Observable

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}
