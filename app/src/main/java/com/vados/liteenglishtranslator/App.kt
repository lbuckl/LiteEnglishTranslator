package com.vados.liteenglishtranslator

import android.app.Application
import com.vados.liteenglishtranslator.di.AppComponent
import com.vados.liteenglishtranslator.di.AppModule
import com.vados.liteenglishtranslator.di.DaggerAppComponent
import com.vados.liteenglishtranslator.koin.DI
import org.koin.core.context.startKoin

/**
 * Основной класс приложения с доступом к контексту
 */
class App : Application() {
    companion object {
        lateinit var instance: App
    }

    //Основной компоненты Dagger 2
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            modules(listOf(DI.appModule))
        }

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}