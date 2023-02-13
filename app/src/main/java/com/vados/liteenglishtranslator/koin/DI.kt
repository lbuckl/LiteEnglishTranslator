package com.vados.liteenglishtranslator.koin

import com.vados.liteenglishtranslator.App
import com.vados.liteenglishtranslator.utils.network.INetworkStatus
import com.vados.liteenglishtranslator.utils.network.NetworkStatus
import com.vados.liteenglishtranslator.utils.scheluders.ISchedulerProvider
import com.vados.liteenglishtranslator.utils.scheluders.SchedulerProvider
import org.koin.core.qualifier.named
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

/**
 * Основной объект koin для внедрения зависимостей
 */
object DI {

    /**
     * Основной модуль
     */
    val appModule = module {
        //Контекст приложения
        single { App.instance.applicationContext }
        //Контроль состояния сети интернет
        single<INetworkStatus> { NetworkStatus(context = get()) }
        //Schedulers provider
        factory(qualifier = named("SchedulerProvider")) {SchedulerProvider()}
    }
}