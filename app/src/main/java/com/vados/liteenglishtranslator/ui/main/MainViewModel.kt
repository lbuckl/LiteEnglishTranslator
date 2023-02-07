package com.vados.liteenglishtranslator.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vados.liteenglishtranslator.model.datasource.local.DataSourceLocal
import com.vados.liteenglishtranslator.model.datasource.remote.DataSourceRemote
import com.vados.liteenglishtranslator.model.domain.AppState
import com.vados.liteenglishtranslator.model.repository.RepositoryImplementation
import com.vados.liteenglishtranslator.ui.interactor.MainInteractor
import com.vados.liteenglishtranslator.utils.scheluders.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class MainViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>(),
    /*private val interactor: MainInteractor = MainInteractor(
        RepositoryImplementation(DataSourceRemote()),
        RepositoryImplementation(DataSourceLocal())
    ),*/
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : ViewModel(), IViewModel {

    @Inject lateinit var schedulerProvider: SchedulerProvider
    @Inject lateinit var interactor: MainInteractor

    val getLiveData = {
        liveData
    }

    override fun getData(word: String, isOnline: Boolean) {
        liveData.postValue(AppState.Loading(100))

        compositeDisposable.add(
            interactor.getData(word,isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { liveData.postValue(AppState.Loading(100)) }
                .subscribeWith(getObserver())
        )
    }

    /**
     * Функция формирующая наблюдателья для получения данных
     */
    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {

            override fun onNext(appState: AppState) {
                liveData.value = appState
            }

            override fun onError(e: Throwable) {
                liveData.value = AppState.Error(e)
            }

            override fun onComplete() {
            }
        }
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}