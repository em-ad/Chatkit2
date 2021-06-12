package com.emad.chatkit2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var messagesViewModel: MessagesViewModel
    lateinit var adapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initViewModels()

    }

    private fun initViewModels() {
        messagesViewModel = ViewModelProvider(this).get(MessagesViewModel::class.java)
        messagesViewModel.liveData.observe(this, Observer {
            if (adapter.itemCount == 0) { //if adapter is empty, submit and scroll to last
                adapter.submitList(it, Runnable {
                    recyclerView.scrollToPosition(it.snapshot().size - 1)
                })
            } else { //if adapter is not empty, only scroll if the newest data is newer than the last adapter data
                val time = it.snapshot().last().time
                val oldTime: Long? = adapter.currentList?.last()?.time
                if (oldTime != null && time > oldTime) //new data inserted
                    adapter.submitList(it, Runnable {
                        recyclerView.scrollToPosition(it.snapshot().size - 1)
                    })
                else adapter.submitList(it)
            }
        })
    }

    private fun initViews() {
        adapter = MessageAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        imageView_send.setOnClickListener(View.OnClickListener {
            var model = MessageModel(UUID.randomUUID().toString())
            model.text = editText_message_input.text.trim().toString()
            model.time = System.currentTimeMillis().minus(1000000)
            DbAccessLayer.getInstance(application).getChatKit()?.chatDao()?.insert(message = model)
            editText_message_input.text.clear()
        })
    }
}