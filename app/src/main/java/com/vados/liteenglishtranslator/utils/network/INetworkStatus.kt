package com.vados.liteenglishtranslator.utils.network

interface INetworkStatus {

    fun initialization()
    fun getStatus(): Boolean
}