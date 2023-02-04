package com.vados.liteenglishtranslator.model.datasource.remote

import com.vados.liteenglishtranslator.model.domain.DataModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Интерфейс для получения данных от Api сайта переводчика
 */
interface ApiService {

    @GET("words/search")
    fun search(@Query("search") wordToSearch: String): Observable<List<DataModel>>
}
