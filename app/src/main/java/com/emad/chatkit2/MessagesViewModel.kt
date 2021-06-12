package com.emad.chatkit2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.PagingData
import com.emad.chatkit2.ChatDataBase.Companion.getInstance

class MessagesViewModel(application: Application) : AndroidViewModel(application) {
    var liveData: LiveData<PagedList<MessageModel>> = LivePagedListBuilder<Int, MessageModel>(
        DbAccessLayer.getInstance(application)
            .getChatKit()!!
            .chatDao()
            .getAll(),
        10)
        .build()
}