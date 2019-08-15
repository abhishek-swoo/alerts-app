package com.example.sampleapplication.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MyDataDao {
    @Query("SELECT * FROM `my-db`")
    fun findData(): MyData

    @Insert
    fun insertAll(data: List<MyData>)

    @Delete
    fun delete(data: MyData)

    @Query("UPDATE `my-db` SET status =:currValue, clear_time =:clearTime")
    fun update(currValue: String, clearTime: Long)

    @Query("Delete FROM `my-db`")
    fun deleteAll()
}