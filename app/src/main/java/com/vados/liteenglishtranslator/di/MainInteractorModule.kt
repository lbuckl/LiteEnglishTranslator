package com.vados.liteenglishtranslator.di

import com.vados.liteenglishtranslator.model.datasource.local.RoomDataBaseImplementation
import com.vados.liteenglishtranslator.model.datasource.remote.RetrofitImplementation
import com.vados.liteenglishtranslator.model.repository.RepositoryImplementation
import com.vados.liteenglishtranslator.ui.interactor.MainInteractor
import dagger.Module
import dagger.Provides

/**
 * Модуль создания объекта MainInteractor
 */
@Module
class MainInteractorModule {

    private val roomDB: RoomDataBaseImplementation = RoomDataBaseImplementation()
    private val remoteProvider: RetrofitImplementation = RetrofitImplementation()

    @Provides
    fun mainInteractor(): MainInteractor {
        return MainInteractor(
            RepositoryImplementation(remoteProvider),
            RepositoryImplementation(roomDB)
        )
    }
}