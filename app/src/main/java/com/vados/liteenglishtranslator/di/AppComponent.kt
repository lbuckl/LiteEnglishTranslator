package com.vados.liteenglishtranslator.di

import com.vados.liteenglishtranslator.model.domain.AppState
import com.vados.liteenglishtranslator.ui.base.BaseActivity
import com.vados.liteenglishtranslator.ui.main.MainActivity
import com.vados.liteenglishtranslator.ui.main.MainViewModel
import com.vados.liteenglishtranslator.viewmodel.ViewModelProvidersFactory
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
        AppModule::class,
        SchedulersModule::class
    ]
)

/**
 * Основной компонент даггер2
 */
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(mainViewModel: MainViewModel)

    //fun viewModelsFactory(): ViewModelProvidersFactory
}