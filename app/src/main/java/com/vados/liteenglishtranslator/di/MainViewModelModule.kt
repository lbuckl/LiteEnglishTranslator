package com.vados.liteenglishtranslator.di

import androidx.lifecycle.ViewModel
import com.vados.liteenglishtranslator.ui.main.MainViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainViewModelModule {

    @Singleton
    @Provides
    fun viewModel(): ViewModel {
        return MainViewModel()
    }
}