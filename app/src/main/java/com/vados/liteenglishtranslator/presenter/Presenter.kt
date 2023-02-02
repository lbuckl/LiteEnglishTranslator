package com.vados.liteenglishtranslator.presenter

import com.vados.liteenglishtranslator.domain.AppState
import com.vados.liteenglishtranslator.ui.base.BaseView

interface Presenter<T : AppState, V : BaseView> {

    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)
}
