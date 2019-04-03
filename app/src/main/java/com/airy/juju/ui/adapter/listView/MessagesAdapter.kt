package com.airy.juju.ui.adapter.listView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.airy.juju.bean.Message
import com.airy.juju.databinding.ListItemMessageBinding


/**
 * Created by Airy on 2019/3/29
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class MessagesAdapter
    (private val onLongClickCallack: (Message) -> Boolean):
        ListAdapter<Message, MessagesAdapter.ViewHolder>(TaskDiffCallback()) {

    class ViewHolder(val binding: ListItemMessageBinding) : RecyclerView.ViewHolder(binding.root)

    class TaskDiffCallback: DiffUtil.ItemCallback<Message>() {
        override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = getItem(position)
        holder.binding.message = message
        holder.binding.root.setOnLongClickListener {
            onLongClickCallack(message)
        }
    }

}