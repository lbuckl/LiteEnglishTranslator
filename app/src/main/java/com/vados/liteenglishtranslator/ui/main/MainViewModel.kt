package com.vados.liteenglishtranslator.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vados.liteenglishtranslator.model.domain.AppState
import com.vados.liteenglishtranslator.ui.interactor.MainInteractor
import com.vados.liteenglishtranslator.utils.parsel.parseSearchResults
import com.vados.liteenglishtranslator.utils.scheluders.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent.inject

class MainViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>(),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : ViewModel(), IViewModel {


    private var resultAppState: AppState? = null

    //region koin inject
    private val schedulerProvider: SchedulerProvider by inject(
        SchedulerProvider::class.java,
        named("SchedulerProvider")
    )

    private val interactor: MainInteractor by inject(
        MainInteractor::class.java,
        named("MainInterActor")
    )
    //endregion

    val getLiveData = {
        liveData
    }

    override suspend fun getData(word: String, isOnline: Boolean) {
        /*compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { liveData.postValue(AppState.Loading(100)) }
                .subscribeWith(getObserver())
        )*/

        interactor.getData(word, isOnline)
    }

    /**
     * Функция формирующая наблюдателья для получения данных
     */
    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {

            override fun onNext(appState: AppState) {
                resultAppState = parseSearchResults(appState)

                liveData.value = resultAppState
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
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