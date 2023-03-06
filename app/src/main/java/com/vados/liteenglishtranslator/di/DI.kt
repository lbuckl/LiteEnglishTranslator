package com.vados.liteenglishtranslator.di

import com.molchanov.domain.model.domain.DataModel
import com.vados.liteenglishtranslator.App
import com.molchanov.data.datasource.DataSource
import com.molchanov.data.datasource.local.DataSourceLocal
import com.molchanov.data.datasource.local.RoomDataBaseImplementation
import com.molchanov.data.datasource.remote.DataSourceRemote
import com.molchanov.data.datasource.remote.RetrofitImplementation
import com.molchanov.data.repository.RepositoryLocal
import com.molchanov.data.repository.RepositoryLocalImplementation
import com.molchanov.data.repository.RepositoryRemote
import com.molchanov.data.repository.RepositoryRemoteImplementation
import com.vados.liteenglishtranslator.ui.interactor.MainInteractor
import com.vados.liteenglishtranslator.ui.main.MainViewModel
import com.molchanov.utils.network.INetworkStatus
import com.molchanov.utils.network.NetworkStatus
import com.vados.liteenglishtranslator.ui.main.MainActivity
import org.koin.androidx.viewmodel.dsl.viewModel
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

    //Модуль для реализации компонентов репозитория
    val repoModule = module {
        single<DataSource<List<DataModel>>>(named("remoteProvider")) {
            DataSourceRemote(RetrofitImplementation())
        }

        single(named("roomDB")) {
            DataSourceLocal(RoomDataBaseImplementation(context = get()))
        }

        single<RepositoryRemote<List<DataModel>>> {
            RepositoryRemoteImplementation(get(named("remoteProvider")))
        }

        single<RepositoryLocal<List<DataModel>>> {
            RepositoryLocalImplementation(get(named("roomDB")))
        }
    }

    //Модуль для реализации компонентов Main
    val mainModule = module {
        //MainInterActor
        factory(qualifier = named("MainInterActor")) {
            MainInteractor(
                get(),
                get()
            )
        }

        scope(named<MainActivity>()){
            scoped {
                MainViewModel()

            }
        }
    }
}