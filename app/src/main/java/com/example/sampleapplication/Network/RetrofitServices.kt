package com.example.sampleapplication.Network

import com.example.sampleapplication.DataNwModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitServices {

    @POST("/alerts/sendAlert")
    fun sendAlertData(@Body body: DataNwModel): Call<Boolean>

}