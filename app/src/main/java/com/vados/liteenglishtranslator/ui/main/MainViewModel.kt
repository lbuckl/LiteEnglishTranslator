package com.vados.liteenglishtranslator.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vados.liteenglishtranslator.model.domain.AppState
import com.vados.liteenglishtranslator.ui.interactor.MainInteractor
import com.vados.liteenglishtranslator.utils.parsel.parseSearchResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent.inject

class MainViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>()
) : ViewModel(), IViewModel {

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
            liveData.postValue(parseSearchResults(interactor.getData(word, isOnline)))
        }
    }

    suspend fun reloadData(isOnline: Boolean) {
        withContext(Dispatchers.IO) {
            liveData.postValue(parseSearchResults(interactor.getData(lastWord, isOnline)))
        }
    }
}