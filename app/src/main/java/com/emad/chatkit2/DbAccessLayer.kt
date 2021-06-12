package com.emad.chatkit2

import android.app.Application
import androidx.room.Room.databaseBuilder

public object DbAccessLayer {

    private var dbAccessLayer: DbAccessLayer? = null
    private lateinit var application: Application
    private var chatDataBase: ChatDataBase? = null

    init {
        dbAccessLayer = this
    }

    fun getInstance(app: Application): DbAccessLayer {
        application = app
        if (dbAccessLayer == null) dbAccessLayer = this
        else if(chatDataBase == null){
            chatDataBase = databaseBuilder(
                application,
                ChatDataBase::class.java,
                "DB_CHATKIT2"
            )
                .allowMainThreadQueries()
                .build();
        }
        return dbAccessLayer!!
    }

    public fun getChatKit() : ChatDataBase?{
        return chatDataBase;
    }

}