package com.vados.liteenglishtranslator.model.datasource.local

import com.vados.liteenglishtranslator.domain.DataModel
import com.vados.liteenglishtranslator.model.datasource.DataSource
import io.reactivex.Observable

class RoomDataBaseImplementation : DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
