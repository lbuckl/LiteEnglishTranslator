package com.vados.liteenglishtranslator.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.vados.liteenglishtranslator.model.domain.AppState
import com.vados.liteenglishtranslator.ui.interactor.MainInteractor
import com.vados.liteenglishtranslator.utils.parsel.parseSearchResults
import com.vados.liteenglishtranslator.utils.scheluders.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class MainViewModel(
    //private val savedStateHandle: SavedStateHandle,
    private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>(),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : ViewModel(), IViewModel {


    private var resultAppState: AppState? = null

    @Inject
    lateinit var schedulerProvider: SchedulerProvider
    @Inject
    lateinit var interactor: MainInteractor

    val getLiveData = {
        //getLastData()
        liveData
    }

    /*fun getLastData(){
        savedStateHandle.getLiveData<AppState>("query").value?.let {
            Log.v("@@@", "last")
            liveData.value = it
        }
    }*/

    /*fun setQuery(appState: AppState) {
        savedStateHandle["query"] = appState
    }*/

    override fun getData(word: String, isOnline: Boolean) {
        liveData.postValue(AppState.Loading(100))

        compositeDisposable.add(
            interactor.getData(word, isOnline)
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
                resultAppState = parseSearchResults(appState)
                //setQuery(resultAppState!!)

                liveData.value = resultAppState
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