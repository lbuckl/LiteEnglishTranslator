package com.vados.liteenglishtranslator.presenter

import com.vados.liteenglishtranslator.domain.AppState
import com.vados.liteenglishtranslator.model.datasource.local.DataSourceLocal
import com.vados.liteenglishtranslator.model.datasource.remote.DataSourceRemote
import com.vados.liteenglishtranslator.model.repository.RepositoryImplementation
import com.vados.liteenglishtranslator.presenter.interactor.MainInteractor
import com.vados.liteenglishtranslator.ui.base.BaseView
import com.vados.liteenglishtranslator.utils.scheluders.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class MainPresenter<T : AppState, V : BaseView> (
    private val interactor: MainInteractor = MainInteractor(
        RepositoryImplementation(DataSourceRemote()),
        RepositoryImplementation(DataSourceLocal())
    ),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val schedulerProvider: SchedulerProvider = SchedulerProvider()
    ) : Presenter<T, V> {

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
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { currentView?.renderData(AppState.Loading(null)) }
                .subscribeWith(getObserver())
        )
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