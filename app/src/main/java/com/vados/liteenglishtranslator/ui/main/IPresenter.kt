package com.vados.liteenglishtranslator.ui.main

import com.vados.liteenglishtranslator.model.domain.AppState
import com.vados.liteenglishtranslator.ui.base.BaseView

interface IPresenter<T : AppState, V : BaseView> {

    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)
}
