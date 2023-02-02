package com.vados.liteenglishtranslator.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vados.liteenglishtranslator.domain.AppState
import com.vados.liteenglishtranslator.presenter.Presenter

abstract class BaseActivity<T : AppState>: AppCompatActivity(), BaseView {

    protected lateinit var presenter: Presenter<T, BaseView>

    protected abstract fun createPresenter(): Presenter<T, BaseView>

    abstract override fun renderData(appState: AppState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }
}