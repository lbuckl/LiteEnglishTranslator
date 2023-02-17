package com.vados.liteenglishtranslator.model.datasource.remote

import com.vados.liteenglishtranslator.model.domain.DataModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Интерфейс для получения данных от Api сайта переводчика
 */
interface ApiService {

    @GET("words/search")
    fun searchAsync(@Query("search") wordToSearch: String): Deferred<List<DataModel>>
}
