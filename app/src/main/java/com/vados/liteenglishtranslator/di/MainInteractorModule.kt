package com.vados.liteenglishtranslator.di

import com.vados.liteenglishtranslator.model.datasource.local.RoomDataBaseImplementation
import com.vados.liteenglishtranslator.model.datasource.remote.RetrofitImplementation
import com.vados.liteenglishtranslator.model.repository.RepositoryImplementation
import com.vados.liteenglishtranslator.ui.interactor.MainInteractor
import dagger.Module
import dagger.Provides

@Module
class MainInteractorModule {

    val roomDB: RoomDataBaseImplementation = RoomDataBaseImplementation()
    val remoteProvider: RetrofitImplementation = RetrofitImplementation()

    @Provides
    fun mainInteractor(): MainInteractor {
        return MainInteractor(
            RepositoryImplementation(remoteProvider),
            RepositoryImplementation(roomDB)
        )
    }
}