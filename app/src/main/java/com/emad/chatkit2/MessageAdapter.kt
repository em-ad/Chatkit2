package com.emad.chatkit2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_message_sample.view.*

class MessageAdapter :
    PagedListAdapter<MessageModel, MessageAdapter.ViewHolder>(MessageDiffCallback) {

    override fun onBindViewHolder(holder: MessageAdapter.ViewHolder, position: Int) {
        val message = getItem(position) ?: return
        holder.itemView.textView_message.text = message.text
        holder.itemView.textView_time.text = message.time.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_message_sample, parent, false)
        )
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    object MessageDiffCallback : DiffUtil.ItemCallback<MessageModel>() {
        override fun areItemsTheSame(oldItem: MessageModel, newItem: MessageModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MessageModel, newItem: MessageModel): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.text == newItem.text
        }
    }

}