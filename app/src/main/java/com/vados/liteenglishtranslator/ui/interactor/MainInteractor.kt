package com.vados.liteenglishtranslator.ui.interactor

import com.molchanov.domain.model.domain.AppState
import com.molchanov.domain.model.domain.DataModel
import com.molchanov.data.repository.RepositoryLocal
import com.molchanov.data.repository.RepositoryRemote

/**
 * Класс интерактора для получения данных локально/через Api
 */
class MainInteractor(
    private val remoteRepository: RepositoryRemote<List<DataModel>>,
    private val localRepository: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return if (fromRemoteSource) {
            val result = remoteRepository.getData(word)
            saveDataToDb(result[0])
            AppState.Success(result)
        } else {
            localRepository.getData(word).let {
                if (it.isNotEmpty()) AppState.Success(localRepository.getData(word))
                else AppState.Error(Throwable("Received empty data "))
            }

        }
    }

    private suspend fun saveDataToDb(translations: DataModel) {
        localRepository.saveDataToDB(translations)
    }
}