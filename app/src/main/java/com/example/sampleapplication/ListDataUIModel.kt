package com.example.sampleapplication

import com.google.gson.annotations.SerializedName

data class ListDataUIModel (
    val alertType: String,
    val id: Long,
    val userId: Long?,
    val description: String,
    val clearTime: Long?,
    val genTime: Long?,
    val status: String
)

data class DataNwModel (
    @SerializedName("alert_type") val alertType: String?,
    @SerializedName("id") val id: Long?,
    @SerializedName("user_id") val userId: Long?,
    @SerializedName("description") val description: String?,
    @SerializedName("clear_time") val clearTime: Long?,
    @SerializedName("generation_time") val genTime: Long?,
    @SerializedName("status") val status: String?
)

data class ListDataNwModel(
    @SerializedName("data") val list: List<DataNwModel>?
)