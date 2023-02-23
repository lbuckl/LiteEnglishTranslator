package com.vados.liteenglishtranslator.di

import com.vados.liteenglishtranslator.App
import com.vados.liteenglishtranslator.model.datasource.DataSource
import com.vados.liteenglishtranslator.model.datasource.local.DataSourceLocal
import com.vados.liteenglishtranslator.model.datasource.local.RoomDataBaseImplementation
import com.vados.liteenglishtranslator.model.datasource.remote.RetrofitImplementation
import com.vados.liteenglishtranslator.model.domain.DataModel
import com.vados.liteenglishtranslator.model.repository.RepositoryImplementation
import com.vados.liteenglishtranslator.model.repository.RepositoryLocalImplementation
import com.vados.liteenglishtranslator.ui.interactor.MainInteractor
import com.vados.liteenglishtranslator.ui.main.MainViewModel
import com.vados.liteenglishtranslator.utils.network.INetworkStatus
import com.vados.liteenglishtranslator.utils.network.NetworkStatus
import com.vados.liteenglishtranslator.utils.scheluders.SchedulerProvider
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Основной объект koin для внедрения зависимостей
 */
object DI {

    //Основной модуль
    val appModule = module {
        //Контекст приложения
        single { App.instance.applicationContext }

        //Контроль состояния сети интернет
        single<INetworkStatus> {
            NetworkStatus(context = get())
        }

        //Schedulers provider
        single(qualifier = named("SchedulerProvider")) {SchedulerProvider()}
    }

    //Модуль для реализации main компонентов
    val mainModule = module {
        single<DataSource<List<DataModel>>> (named("remoteProvider")) {
            RetrofitImplementation()
        }
        single (named("roomDB")) {
            DataSourceLocal(RoomDataBaseImplementation(context = get()))
        }

        //MainInterActor
        factory(qualifier = named("MainInterActor")){
            MainInteractor(
                RepositoryImplementation(get(named("remoteProvider"))),
                RepositoryLocalImplementation(get(named("roomDB")))
            )
        }
        //MainViewModel
        single { MainViewModel() }
    }
}