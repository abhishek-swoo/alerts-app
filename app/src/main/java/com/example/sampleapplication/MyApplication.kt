package com.example.sampleapplication

import android.app.Application
import com.example.sampleapplication.broadcastreceiver.NetworkChangeReceiver
import android.net.ConnectivityManager
import android.content.IntentFilter

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this
        val intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(NetworkChangeReceiver(), intentFilter)
    }

    fun setConnectivityListener(listener: NetworkChangeReceiver.ConnectivityReceiverListener) {
        NetworkChangeReceiver.connectivityReceiverListener = listener
    }

    companion object {

        @get:Synchronized
        var instance: MyApplication? = null
            private set
    }
}