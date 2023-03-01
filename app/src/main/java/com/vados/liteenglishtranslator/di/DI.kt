package com.vados.liteenglishtranslator.di

import com.molchanov.domain.model.domain.DataModel
import com.vados.liteenglishtranslator.App
import com.vados.liteenglishtranslator.model.datasource.DataSource
import com.vados.liteenglishtranslator.model.datasource.local.DataSourceLocal
import com.vados.liteenglishtranslator.model.datasource.local.RoomDataBaseImplementation
import com.vados.liteenglishtranslator.model.datasource.remote.DataSourceRemote
import com.vados.liteenglishtranslator.model.datasource.remote.RetrofitImplementation
import com.vados.liteenglishtranslator.model.repository.RepositoryLocalImplementation
import com.vados.liteenglishtranslator.model.repository.RepositoryRemoteImplementation
import com.vados.liteenglishtranslator.ui.interactor.MainInteractor
import com.vados.liteenglishtranslator.ui.main.MainViewModel
import com.molchanov.utils.network.INetworkStatus
import com.molchanov.utils.network.NetworkStatus
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
    }

    //Модуль для реализации main компонентов
    val mainModule = module {

        single<DataSource<List<DataModel>>>(named("remoteProvider")) {
            DataSourceRemote(RetrofitImplementation())
        }

        single(named("roomDB")) {
            DataSourceLocal(RoomDataBaseImplementation(context = get()))
        }

        //MainInterActor
        factory(qualifier = named("MainInterActor")) {
            MainInteractor(
                RepositoryRemoteImplementation(get(named("remoteProvider"))),
                RepositoryLocalImplementation(get(named("roomDB")))
            )
        }
        //MainViewModel
        single { MainViewModel() }
    }
}