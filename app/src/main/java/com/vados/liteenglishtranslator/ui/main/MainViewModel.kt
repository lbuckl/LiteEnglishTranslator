package com.vados.liteenglishtranslator.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.molchanov.domain.model.domain.AppState
import com.molchanov.utils.parsel.parseSearchResults
import com.vados.liteenglishtranslator.ui.interactor.MainInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent.inject

class MainViewModel(
) : ViewModel(), IViewModel {

    private val _liveData: MutableLiveData<AppState> = MutableLiveData<AppState>()

    private val liveData: LiveData<AppState> = _liveData

    private var lastWord = ""

    private val interactor: MainInteractor by inject(
        MainInteractor::class.java,
        named("MainInterActor")
    )

    val getLiveData = {
        liveData
    }

    override suspend fun getData(word: String, isOnline: Boolean) {

        lastWord = word

        withContext(Dispatchers.IO) {
            _liveData.postValue(parseSearchResults(interactor.getData(word, isOnline)))
        }
    }

    suspend fun reloadData(isOnline: Boolean) {
        withContext(Dispatchers.IO) {
            _liveData.postValue(parseSearchResults(interactor.getData(lastWord, isOnline)))
        }
    }
}