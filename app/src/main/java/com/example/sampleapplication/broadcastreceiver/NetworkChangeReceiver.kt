package com.example.sampleapplication.broadcastreceiver

import android.net.ConnectivityManager
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import com.example.sampleapplication.Constants
import com.example.sampleapplication.MainActivityViewModel
import com.example.sampleapplication.MyApplication
import com.example.sampleapplication.db.AppDatabase
import com.example.sampleapplication.db.MyData
import com.example.sampleapplication.db.MyDataDao
import kotlinx.coroutines.experimental.launch


class NetworkChangeReceiver : BroadcastReceiver() {
    lateinit var myDataDao: MyDataDao
    var myDataList: ArrayList<MyData> = ArrayList<MyData>()
    private lateinit var viewModel: MainActivityViewModel

    override fun onReceive(context: Context, arg1: Intent) {
        val cm = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        val isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting
        myDataDao = AppDatabase.get(context).myDataDao()
        if (!isConnected) {
            launch {
                myDataList.add(
                    MyData(
                        alertId = 0, alertType = Constants.ATERT_TYPE_NETWORK, description = "",
                        genTime = System.currentTimeMillis(), cleaTime = -1, status = Constants.NETWORK_STATUS_DISCONNECTED
                    )
                )
                myDataDao.insertAll(myDataList)
            }

        } else {
            launch {
                myDataDao.update(Constants.NETWORK_STATUS_CONNECTED, System.currentTimeMillis())
            }

        }


        if (connectivityReceiverListener != null) {
            connectivityReceiverListener!!.onNetworkConnectionChanged(isConnected)
        }
    }


    interface ConnectivityReceiverListener {
        fun onNetworkConnectionChanged(isConnected: Boolean)
    }

    companion object {

        var connectivityReceiverListener: ConnectivityReceiverListener? = null

        val isConnected: Boolean
            get() {
                val cm =
                    MyApplication.instance?.applicationContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork = cm.activeNetworkInfo
                return activeNetwork != null && activeNetwork.isConnectedOrConnecting
            }
    }
}