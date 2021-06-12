package com.emad.chatkit2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            adapter.submitList(it)
        })
    }

    private fun initViews() {
        adapter = MessageAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        imageView_send.setOnClickListener(View.OnClickListener {
            var model = MessageModel(UUID.randomUUID().toString())
            model.text = editText_message_input.text.trim().toString()
            model.time = System.currentTimeMillis()
            DbAccessLayer.getInstance(application).getChatKit()?.chatDao()?.insert(message = model)
            editText_message_input.text.clear()
        })
    }
}