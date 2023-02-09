package com.vados.liteenglishtranslator.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vados.liteenglishtranslator.ui.main.MainViewModel
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * Фабрика для создания и инъекции ViewModel
 * работает по принципу хранения моделей по мапинге
 * ключем является класс ViewModel
 */
@Singleton
class ViewModelProvidersFactory @Inject constructor(
    myViewModelProvider: Provider<ViewModel>
) : ViewModelProvider.Factory {

    //мапинг для храрнения моделей
    private val providers = mapOf<Class<*>, Provider<out ViewModel>>(
        MainViewModel::class.java to myViewModelProvider
    )

    //функция создания новых моделей
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return providers[modelClass]!!.get() as T
    }
}