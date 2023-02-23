package com.vados.liteenglishtranslator.ui.interactor

import android.util.Log
import com.vados.liteenglishtranslator.model.domain.AppState
import com.vados.liteenglishtranslator.model.domain.DataModel
import com.vados.liteenglishtranslator.model.repository.Repository
import com.vados.liteenglishtranslator.model.repository.RepositoryLocal

/**
 * Класс интерактора для получения данных локально/через Api
 */
class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return if (fromRemoteSource) {
            val result = remoteRepository.getData(word)
            saveDataToDb(result[0])
                AppState.Success(result)
        } else {
                Log.v("@@@", "MainInteractor: localRepository")
                AppState.Success(localRepository.getData(word))
        }
    }

    private suspend fun saveDataToDb(translations: DataModel){
        localRepository.saveDataToDB(translations)
    }
}