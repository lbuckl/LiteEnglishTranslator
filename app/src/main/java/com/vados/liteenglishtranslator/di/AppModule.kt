package com.vados.liteenglishtranslator.di

import com.vados.liteenglishtranslator.App
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }
}