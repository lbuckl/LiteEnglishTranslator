package com.vados.liteenglishtranslator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vados.liteenglishtranslator.ui.main.MainViewModel
import javax.inject.Inject
import javax.inject.Provider

class ViewModelProvidersFactory @Inject constructor(
    myViewModelProvider: Provider<ViewModel>
): ViewModelProvider.Factory {

    private val providers = mapOf<Class<*>, Provider<out ViewModel>>(
        MainViewModel::class.java to myViewModelProvider
    )

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return providers[modelClass]!!.get() as T
    }
}