package com.vados.liteenglishtranslator.ui.interactor

import android.util.Log
import com.vados.liteenglishtranslator.model.domain.AppState
import com.vados.liteenglishtranslator.model.domain.DataModel
import com.vados.liteenglishtranslator.model.repository.Repository

/**
 * Класс интерактора для получения данных локально/через Api
 */
class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return if (fromRemoteSource) {
                AppState.Success(remoteRepository.getData(word))
        } else {
                Log.v("@@@", "MainInteractor: localRepository")
                AppState.Success(localRepository.getData(word))
        }
    }
}