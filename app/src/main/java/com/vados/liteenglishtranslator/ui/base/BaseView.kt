package com.vados.liteenglishtranslator.ui.base

import com.molchanov.domain.model.domain.AppState

/**
 * Базовый интерфейс для реализации BaseActivity
 */
interface BaseView {
    fun renderData(appState: AppState)
}
