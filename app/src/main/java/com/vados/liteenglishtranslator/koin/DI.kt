package com.vados.liteenglishtranslator.koin

import com.vados.liteenglishtranslator.App
import com.vados.liteenglishtranslator.model.datasource.local.RoomDataBaseImplementation
import com.vados.liteenglishtranslator.model.datasource.remote.RetrofitImplementation
import com.vados.liteenglishtranslator.model.repository.RepositoryImplementation
import com.vados.liteenglishtranslator.ui.interactor.MainInteractor
import com.vados.liteenglishtranslator.utils.network.INetworkStatus
import com.vados.liteenglishtranslator.utils.network.NetworkStatus
import com.vados.liteenglishtranslator.utils.scheluders.SchedulerProvider
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Основной объект koin для внедрения зависимостей
 */
object DI {

    private val roomDB: RoomDataBaseImplementation = RoomDataBaseImplementation()
    private val remoteProvider: RetrofitImplementation = RetrofitImplementation()

    /**
     * Основной модуль
     */
    val appModule = module {
        //Контекст приложения
        single { App.instance.applicationContext }
        //Контроль состояния сети интернет
        single<INetworkStatus> { NetworkStatus(context = get()) }
        //Schedulers provider
        single(qualifier = named("SchedulerProvider")) {SchedulerProvider()}
    }

    val mainModule = module {
        //MainInterActor
        factory (qualifier = named("MainInterActor")){
            MainInteractor(
                RepositoryImplementation(remoteProvider),
                RepositoryImplementation(roomDB)
            )
        }
    }
}