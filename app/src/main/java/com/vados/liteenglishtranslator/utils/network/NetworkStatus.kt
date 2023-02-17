package com.vados.liteenglishtranslator.utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class NetworkStatus(context: Context, networkChanel: Channel<Boolean>) : INetworkStatus {

    private val ioScope = CoroutineScope(Dispatchers.IO)

    private var networkStatus by Delegates.notNull<Boolean>()

    init {
        ioScope.launch {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val request = NetworkRequest.Builder().build()

            try {
                networkStatus = connectivityManager.activeNetworkInfo!!.isConnected
                ioScope.launch { networkChanel.send(true) }
            }catch (e: NullPointerException){
                networkStatus = false
                ioScope.launch { networkChanel.send(false) }
            }

            connectivityManager.registerNetworkCallback(request, object :
                ConnectivityManager.NetworkCallback() {

                override fun onAvailable(network: Network) {
                    networkStatus = true
                    ioScope.launch { networkChanel.send(true) }
                }

                override fun onUnavailable() {
                    //TODO nothing
                }

                override fun onLost(network: Network) {
                    networkStatus = false
                    ioScope.launch { networkChanel.send(false)}
                }
            })
        }
    }

    override fun initialization() {
        //TODO nothing
    }

    override fun getStatus(): Boolean {
        return networkStatus
    }

}
