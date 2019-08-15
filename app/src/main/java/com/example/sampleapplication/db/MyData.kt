package com.example.sampleapplication.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "my-db")
data class MyData (
    @PrimaryKey(autoGenerate = true) val alertId: Long,
    @ColumnInfo(name = "alert_type") val alertType: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "generation_time") val genTime: Long?,
    @ColumnInfo(name = "clear_time") val cleaTime: Long?,
    @ColumnInfo(name = "status") val status: String?
) {
}