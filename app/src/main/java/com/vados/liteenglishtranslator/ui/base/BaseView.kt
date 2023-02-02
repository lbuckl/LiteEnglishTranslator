package com.vados.liteenglishtranslator.ui.base

import com.vados.liteenglishtranslator.domain.AppState

interface BaseView {

    fun renderData(appState: AppState)

}
