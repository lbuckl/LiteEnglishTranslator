package com.vados.liteenglishtranslator.di.viewmodel

import androidx.lifecycle.ViewModel
import com.vados.liteenglishtranslator.ui.main.MainViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Модуль создания синглтона ViewModel
 */
@Module
class MainViewModelModule {

    @Singleton
    @Provides
    fun viewModel(): ViewModel {
        return MainViewModel()
    }
}