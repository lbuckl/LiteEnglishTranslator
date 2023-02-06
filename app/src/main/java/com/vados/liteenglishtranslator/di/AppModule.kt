package com.vados.liteenglishtranslator.di

import com.vados.liteenglishtranslator.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }
}