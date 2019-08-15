package com.example.sampleapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampleapplication.Network.RetrofitProvider
import retrofit2.Call
import retrofit2.Response


class MainActivityViewModel : ViewModel() {

    val aRetrofitServices by lazy { RetrofitProvider.instance.services }

    var entrySent = MutableLiveData<Boolean>()


    fun sendAlertsData(dataNwModel: DataNwModel) {
        val a = aRetrofitServices.sendAlertData(dataNwModel)
        a.enqueue(object : retrofit2.Callback<Boolean> {
            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Log.d("RetrofitCall", "failed: " + t.message + " " + t.stackTrace[0])
                entrySent.postValue(false)
            }

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                Log.d("RetrofitCall", "success: " + response.body())
                entrySent.postValue(true)
            }
        })
    }

}