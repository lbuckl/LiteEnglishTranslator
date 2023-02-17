package com.vados.liteenglishtranslator.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vados.liteenglishtranslator.model.domain.AppState
import com.vados.liteenglishtranslator.ui.interactor.MainInteractor
import com.vados.liteenglishtranslator.utils.parsel.parseSearchResults
import com.vados.liteenglishtranslator.utils.scheluders.SchedulerProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent.inject

class MainViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>()
) : ViewModel(), IViewModel {


    private var resultAppState: AppState? = null

    //region koin inject
    private val schedulerProvider: SchedulerProvider by inject(
        SchedulerProvider::class.java,
        named("SchedulerProvider")
    )

    private val interactor: MainInteractor by inject(
        MainInteractor::class.java,
        named("MainInterActor")
    )
    //endregion

    val getLiveData = {
        liveData
    }

    override suspend fun getData(word: String, isOnline: Boolean) {

        withContext(Dispatchers.IO){
            liveData.postValue(parseSearchResults(interactor.getData(word, isOnline)))
        }
    }
}