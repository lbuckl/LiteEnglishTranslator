package com.vados.liteenglishtranslator.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.vados.liteenglishtranslator.App
import com.vados.liteenglishtranslator.model.domain.AppState
import com.vados.liteenglishtranslator.ui.main.MainActivity

/***
 * Базовый класс для реализации активити в формате MVP
 */
abstract class BaseActivity<T : AppState>: AppCompatActivity(), BaseView {

    abstract override fun renderData(appState: AppState)

}