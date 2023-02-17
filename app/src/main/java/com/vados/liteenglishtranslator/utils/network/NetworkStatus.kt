package com.vados.liteenglishtranslator.utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

/**
 * Функция состояния контроля сети
 * имеет 2 режима работы:
 * @param networkStatus - возвращает последнее значение статуса сети
 * @param networkChanel - возвращает корутину-поток
 */
class NetworkStatus(val context: Context) : INetworkStatus {

    private val ioScope = CoroutineScope(Dispatchers.IO)

    private var networkStatus by Delegates.notNull<Boolean>()

    private var isInit: Boolean = false

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val request = NetworkRequest.Builder().build()

    init {
        ioScope.launch {
            connectivityManager.registerNetworkCallback(request, object :
                ConnectivityManager.NetworkCallback() {

                override fun onAvailable(network: Network) {
                    networkStatus = true
                }

                override fun onUnavailable() {
                    //TODO nothing
                }

                override fun onLost(network: Network) {
                    networkStatus = false
                }
            })
        }
    }

    /**
     * Функция выполняет первичную проверку состояния сети (холодный старт)
     */
    private fun initialize(): Boolean {
        networkStatus = try {
            connectivityManager.activeNetworkInfo!!.isConnected
        }catch (e: NullPointerException){
            false
        }

        isInit = true
        return networkStatus
    }

    /**
     * Функция получения состояни сети
     */
    override fun getStatus(): Boolean {
        if (!isInit){
            return initialize()
        }
        return networkStatus
    }
}
