package com.vados.liteenglishtranslator.di

import com.vados.liteenglishtranslator.model.domain.AppState
import com.vados.liteenglishtranslator.ui.base.BaseActivity
import com.vados.liteenglishtranslator.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Основной компонент Dagger 2
 * Содежит:
 * (modules) - список модулей
 * (interface AppComponentDagger) - набор классов, в которые производятся инъекции
 */
@Singleton
@Component(
    modules = [
        //Базовые компоненты:
        AppModule::class
    ]
)

/**
 * Основной компонент даггер2
 */
interface AppComponent {
    //fun inject(baseActivity: BaseActivity<AppState>)
}