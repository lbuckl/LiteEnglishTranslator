package com.vados.liteenglishtranslator.presenter

import com.vados.liteenglishtranslator.domain.AppState
import com.vados.liteenglishtranslator.ui.base.BaseView
import io.reactivex.observers.DisposableObserver

class MainPresenter<T : AppState, V : BaseView> : Presenter<T, V> {

    //ссылка на интерфейс Вью
    private var currentView: V? = null

    override fun attachView(view: V) {
        if (view != currentView) {
            currentView = view
        }
    }

    override fun detachView(view: V) {
        //TODO("Not yet implemented")
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        //TODO("Not yet implemented")
    }

    /**
     * Функция формирующая наблюдателья для получения данных
     */
    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {

            override fun onNext(appState: AppState) {
                currentView?.renderData(appState)
            }

            override fun onError(e: Throwable) {
                currentView?.renderData(AppState.Error(e))
            }

            override fun onComplete() {
            }
        }
    }

}