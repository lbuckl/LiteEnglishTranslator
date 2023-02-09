package com.vados.liteenglishtranslator.di

import com.vados.liteenglishtranslator.App
import com.vados.liteenglishtranslator.utils.network.INetworkStatus
import com.vados.liteenglishtranslator.utils.network.NetworkStatus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Модуль создаёт и возвращает средство мониторинга интернет соединения
 */
@Module
class NetworkStatusModule {

    @Singleton
    @Provides
    fun networkStatus(app: App): INetworkStatus = NetworkStatus(app.applicationContext)
}