package com.example.sampleapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.sampleapplication.broadcastreceiver.NetworkChangeReceiver
import com.example.sampleapplication.databinding.FragmentMainBinding
import com.example.sampleapplication.db.AppDatabase
import com.example.sampleapplication.db.MyDataDao
import kotlinx.coroutines.experimental.launch

class MainFragment: Fragment(), NetworkChangeReceiver.ConnectivityReceiverListener {


    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: FragmentMainBinding
    private lateinit var dataDao: MyDataDao

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        dataDao = AppDatabase.get(context!!.applicationContext).myDataDao()

        viewModel.entrySent.observe(this, Observer {
            if(it) {
                dataDao.deleteAll()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        MyApplication.instance?.setConnectivityListener(this)
    }


    override fun onNetworkConnectionChanged(isConnected: Boolean) {

        launch {
            val dbData = dataDao.findData()
            val data = DataNwModel(
                id = dbData.alertId,
                userId = 2020,
                genTime = dbData.genTime,
                clearTime = dbData.cleaTime,
                status = dbData.status,
                description = dbData.description,
                alertType = dbData.alertType

            )
            viewModel.sendAlertsData(data)
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}