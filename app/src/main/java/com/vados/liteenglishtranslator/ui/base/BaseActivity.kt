package com.vados.liteenglishtranslator.ui.base

import androidx.appcompat.app.AppCompatActivity
import com.molchanov.domain.model.domain.AppState

/***
 * Базовый класс для реализации активити в формате MVP
 */
abstract class BaseActivity<T : AppState> : AppCompatActivity(), BaseView {

    abstract override fun renderData(appState: AppState)

}