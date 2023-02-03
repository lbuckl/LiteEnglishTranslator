package com.vados.liteenglishtranslator.presenter.interactor

import com.vados.liteenglishtranslator.domain.AppState
import com.vados.liteenglishtranslator.domain.DataModel
import com.vados.liteenglishtranslator.model.repository.Repository
import io.reactivex.Observable

class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map {
                AppState.Success(it)
            }
        } else {
            localRepository.getData(word).map {
                AppState.Success(it)
            }
        }
    }
}