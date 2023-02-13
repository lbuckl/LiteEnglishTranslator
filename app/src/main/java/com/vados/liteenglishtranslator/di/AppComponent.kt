package com.vados.liteenglishtranslator.di

import com.vados.liteenglishtranslator.di.viewmodel.MainViewModelModule
import com.vados.liteenglishtranslator.di.viewmodel.ViewModelProvidersFactory
import com.vados.liteenglishtranslator.ui.main.MainActivity
import com.vados.liteenglishtranslator.ui.main.MainViewModel
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
        AppModule::class,
        MainViewModelModule::class
    ]
)

/**
 * Основной компонент даггер2
 */
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainViewModel: MainViewModel)

    fun viewModelsFactory(): ViewModelProvidersFactory
}