package com.vados.liteenglishtranslator.di

import com.vados.liteenglishtranslator.utils.scheluders.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class SchedulersModule {

    @Provides
    fun scheduleProvider(): SchedulerProvider = SchedulerProvider()
}