package com.emad.chatkit2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.util.Log
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [MessageModel::class], version = 1, exportSchema = false)
abstract class ChatDataBase : RoomDatabase() {
    abstract fun chatDao(): ChatDao

    companion object {
        private var instance: ChatDataBase? = null

        @Synchronized
        fun getInstance(context: Context): ChatDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChatDataBase::class.java,
                    "chat_database"
                ).addCallback(roomCallback)
                    .build()
            }
            return instance!!
        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                Log.e("TAG", "onCreate: " + db.path + " " + db.beginTransaction().javaClass.canonicalName )
            }
        }
    }
}