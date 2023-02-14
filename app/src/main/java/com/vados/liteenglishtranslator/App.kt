package com.vados.liteenglishtranslator

import android.app.Application
import com.vados.liteenglishtranslator.di.DI
import org.koin.core.context.startKoin

/**
 * Основной класс приложения с доступом к контексту
 */
class App : Application() {
    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            modules(listOf(DI.appModule, DI.mainModule))
        }
    }

}