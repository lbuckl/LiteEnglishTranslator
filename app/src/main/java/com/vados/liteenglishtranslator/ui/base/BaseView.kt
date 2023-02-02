package com.vados.liteenglishtranslator.ui.base

import com.vados.liteenglishtranslator.domain.AppState

/**
 * Базовый интерфейс для реализации BaseActivity
 */
interface BaseView {
    fun renderData(appState: AppState)
}
