package com.vados.liteenglishtranslator.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.molchanov.domain.model.domain.AppState
import com.molchanov.domain.model.domain.DataModel
import com.molchanov.utils.getImage
import com.molchanov.utils.network.INetworkStatus
import com.vados.liteenglishtranslator.R
import com.vados.liteenglishtranslator.databinding.ActivityMainBinding
import com.vados.liteenglishtranslator.ui.SearchDialogFragment
import com.vados.liteenglishtranslator.ui.base.BaseActivity
import com.vados.liteenglishtranslator.ui.viewById
import kotlinx.coroutines.*
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject

/**
 * Активити реализующая работу переводчика
 */
class MainActivity : BaseActivity<AppState>() {

    private val mainCoroutineScope = CoroutineScope(Dispatchers.Main)
    private val ioCoroutineScope = CoroutineScope(Dispatchers.IO)

    private lateinit var binding: ActivityMainBinding

    private var adapter: MainRVAdapter? = null

    //region Koin implementation
    private val networkStatus: INetworkStatus by inject()

    private val mainKoinScope = getKoin().getOrCreateScope<MainActivity>("myScope")

    private val viewModel: MainViewModel by mainKoinScope.inject()
    //endregion

    /**
     * Лисенер от элементов RecyclerView
     */
    private val onListItemClickListener: MainRVAdapter.OnListItemClickListener =
        object : MainRVAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {

                ioCoroutineScope.launch {
                    val icon = getImage(this@MainActivity, data.meanings!![0].imageUrl)

                    withContext(Dispatchers.Main) {

                        binding.successLinearLayout.visibility = View.GONE
                        binding.imageLayout.visibility = View.VISIBLE

                        binding.ivDetails.setImageDrawable(icon)
                    }
                }
                //Toast.makeText(this@MainActivity, data.text, Toast.LENGTH_SHORT).show()
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

                if (dataModel.isNullOrEmpty()) {
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
                    binding.progressBarHorizontal.progress = appState.progress!!
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
        installSplashScreen()

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initFabClickListener()

        initViewModel()

        initImageCloseButton()
    }

    private fun initViewModel() {
        viewModel.getLiveData().observe(this) {
            renderData(it)
        }
    }

    private fun initImageCloseButton() {
        binding.btnCloseImage.setOnClickListener {
            binding.imageLayout.visibility = View.GONE
            binding.successLinearLayout.visibility = View.VISIBLE
        }
    }

    /**
     * Функция инициализирует и управляет действиями
     * кнопки поиска/перевода слова
     */
    private fun initFabClickListener() {
        val searchFAB by viewById<FloatingActionButton>(R.id.search_fab)

        searchFAB.setOnClickListener {
            //Создаём диологовое окно
            val searchDialogFragment = SearchDialogFragment.newInstance()

            //Инициализируем прослушку
            searchDialogFragment.setOnSearchClickListener(object :
                SearchDialogFragment.OnSearchClickListener {

                //послаем запрос на перевод слова приходящего колбэком
                override fun onClick(searchWord: String) {
                    dataRequest(searchWord)
                }
            })

            //отображаем диологовое окно
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
    }

    private fun dataRequest(searchWord: String) {
        networkStatus.getStatus().let {
            if (!it) {
                Toast.makeText(
                    this@MainActivity,
                    "Связь отсутствует",
                    Toast.LENGTH_SHORT
                )
                    .show()
                mainCoroutineScope.launch {
                    viewModel.getData(searchWord, false)
                }
            } else {
                mainCoroutineScope.launch {
                    viewModel.getData(searchWord, true)
                }
            }
        }
    }

    private fun dataReload() {
        networkStatus.getStatus().let {
            if (!it) {
                Toast.makeText(
                    this@MainActivity,
                    "Связь отсутствует",
                    Toast.LENGTH_SHORT
                )
                    .show()
                mainCoroutineScope.launch {
                    viewModel.reloadData(false)
                }
            } else {
                mainCoroutineScope.launch {
                    viewModel.reloadData(true)
                }
            }
        }
    }

    /**
     * Дейсвия при ошибке
     */
    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextview.text = error ?: getString(R.string.undefined_error)
        binding.reloadButton.setOnClickListener {
            dataReload()
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

    override fun onDestroy() {
        super.onDestroy()

        ioCoroutineScope.cancel()
        mainCoroutineScope.cancel()

        mainKoinScope.close()
    }
}