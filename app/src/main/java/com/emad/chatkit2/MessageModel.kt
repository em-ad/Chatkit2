package com.emad.chatkit2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_table")
data class MessageModel(@PrimaryKey val id: String) {
    lateinit var text: String
    var time: Long = -1L
}