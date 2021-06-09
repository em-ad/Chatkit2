package com.emad.chatkit2

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface ChatDao {

    @Insert
    fun insert(message: MessageModel)

    
}