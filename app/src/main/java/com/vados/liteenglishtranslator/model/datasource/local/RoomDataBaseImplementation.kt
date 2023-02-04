package com.vados.liteenglishtranslator.model.datasource.local

import com.vados.liteenglishtranslator.model.domain.DataModel
import com.vados.liteenglishtranslator.model.datasource.DataSource
import io.reactivex.Observable

/**
 * Класс для реализации получения данных из БД Room
 */
class RoomDataBaseImplementation : DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
