package com.vados.liteenglishtranslator.utils.scheluders

import io.reactivex.Scheduler

/**
 * Интерфес для преодставления потоков RxJava
 */
interface ISchedulerProvider {

    fun ui(): Scheduler

    fun io(): Scheduler
}