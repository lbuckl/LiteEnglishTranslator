package com.vados.liteenglishtranslator

import android.app.Application
import com.vados.liteenglishtranslator.di.AppComponent
import com.vados.liteenglishtranslator.di.AppModule
import com.vados.liteenglishtranslator.di.DaggerAppComponent

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

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}