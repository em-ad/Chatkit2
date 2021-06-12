package com.emad.chatkit2

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ChatDao {

    @Insert
    fun insert(message: MessageModel)

    @Query("SELECT * FROM 'message_table' ORDER BY time ASC")
    fun getAll(): DataSource.Factory<Int, MessageModel>

}