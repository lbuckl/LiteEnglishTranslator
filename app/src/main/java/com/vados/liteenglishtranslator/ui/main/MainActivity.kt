package com.vados.liteenglishtranslator.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.vados.liteenglishtranslator.App
import com.vados.liteenglishtranslator.R
import com.vados.liteenglishtranslator.databinding.ActivityMainBinding
import com.vados.liteenglishtranslator.model.domain.AppState
import com.vados.liteenglishtranslator.model.domain.DataModel
import com.vados.liteenglishtranslator.ui.SearchDialogFragment
import com.vados.liteenglishtranslator.ui.base.BaseActivity
import com.vados.liteenglishtranslator.utils.network.INetworkStatus
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import org.koin.android.ext.android.inject

/**
 * Активити реализующая работу переводчика
 */
class MainActivity : BaseActivity<AppState>() {

    private lateinit var binding: ActivityMainBinding

    private var adapter: MainRVAdapter? = null

    val vm: MainViewModel by viewModels {
        App.instance.appComponent.viewModelsFactory()
    }

    private val networkStatus: INetworkStatus by inject()


    /**
     * Лисенер от элементов RecyclerView
     */
    private val onListItemClickListener: MainRVAdapter.OnListItemClickListener =
        object : MainRVAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                Toast.makeText(this@MainActivity, data.text, Toast.LENGTH_SHORT).show()
            }
        }

    /**
     * Основная функция (местная бизнес логика фрагмента)
     * отображает информацию в зависиомти от состояния appState
     */
    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data

                if (dataModel == null || dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    if (adapter == null) {
                        binding.mainActivityRecyclerview.layoutManager =
                            LinearLayoutManager(applicationContext)
                        binding.mainActivityRecyclerview.adapter =
                            MainRVAdapter(onListItemClickListener, dataModel)
                    } else {
                        adapter!!.setData(dataModel)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()

                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = View.VISIBLE
                    binding.progressBarRound.visibility = View.GONE
                    binding.progressBarHorizontal.progress = appState.progress
                } else {
                    binding.progressBarHorizontal.visibility = View.GONE
                    binding.progressBarRound.visibility = View.VISIBLE
                }
            }
            is AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.instance.appComponent.inject(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFabClickListener()

        initViewModel()
    }

    private fun initViewModel() {
        App.instance.appComponent.inject(vm)

        vm.getLiveData().observe(this) {
            renderData(it)
        }
    }

    /**
     * Функция инициализирует и управляет действиями
     * кнопки поиска/перевода слова
     */
    private fun initFabClickListener() {
        binding.searchFab.setOnClickListener {
            //Создаём диологовое окно
            val searchDialogFragment = SearchDialogFragment.newInstance()

            //Инициализируем прослушку
            searchDialogFragment.setOnSearchClickListener(object :
                SearchDialogFragment.OnSearchClickListener {

                //послаем запрос на перевод слова приходящего колбэком
                override fun onClick(searchWord: String) {

                    networkStatus.isOnlineSingle()
                        .map {
                            if (!it)
                                Toast.makeText(
                                    this@MainActivity,
                                    "Связь отсутствует",
                                    Toast.LENGTH_SHORT)
                                    .show()
                            else vm.getData(searchWord, true)
                        }
                        .subscribe()
                }
            })

            //отображаем диологовое окно
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
    }



    /**
     * Дейсвия при ошибке
     */
    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextview.text = error ?: getString(R.string.undefined_error)
        binding.reloadButton.setOnClickListener {
            vm.getData("hi", true)
        }
    }

    /**
     * Дейсвия при удачном запросе
     */
    private fun showViewSuccess() {
        binding.successLinearLayout.visibility = View.VISIBLE
        binding.loadingFrameLayout.visibility = View.GONE
        binding.errorLinearLayout.visibility = View.GONE
    }

    /**
     * Дейсвия в момент загрузки данных
     */
    private fun showViewLoading() {
        binding.successLinearLayout.visibility = View.GONE
        binding.loadingFrameLayout.visibility = View.VISIBLE
        binding.errorLinearLayout.visibility = View.GONE
    }

    private fun showViewError() {
        binding.successLinearLayout.visibility = View.GONE
        binding.loadingFrameLayout.visibility = View.GONE
        binding.errorLinearLayout.visibility = View.VISIBLE
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }
}