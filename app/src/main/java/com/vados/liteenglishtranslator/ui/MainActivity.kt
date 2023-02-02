package com.vados.liteenglishtranslator.ui

import android.os.Bundle
import com.vados.liteenglishtranslator.databinding.ActivityMainBinding
import com.vados.liteenglishtranslator.domain.AppState
import com.vados.liteenglishtranslator.presenter.MainPresenter
import com.vados.liteenglishtranslator.presenter.Presenter
import com.vados.liteenglishtranslator.ui.base.BaseActivity
import com.vados.liteenglishtranslator.ui.base.BaseView

/**
 * Активити реализующая работу переводчика
 */
class MainActivity: BaseActivity<AppState>() {

    private lateinit var binding: ActivityMainBinding

    override fun createPresenter(): Presenter<AppState, BaseView> {
        return MainPresenter()
    }

    override fun renderData(appState: AppState) {
        //TODO(Необходима реализация вывода данных на кэран)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}