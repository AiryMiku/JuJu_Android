package com.airy.juju.ui.adapter.listView

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.airy.juju.bean.Message
import com.airy.juju.databinding.ListItemMessageBinding
import com.airy.juju.util.UserCenter


/**
 * Created by Airy on 2019/3/29
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class MessagesAdapter(private val onLongClickCallack: (Message)->Unit):
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
        Log.d("MsgAdapter", message.toString())
        when(message.type) {
            0 -> {
                holder.binding.right.visibility = View.VISIBLE
                holder.binding.left.visibility = View.GONE
            }
            1 -> {
                if (message.to_id == UserCenter.getUserId()) {
                    holder.binding.right.visibility = View.VISIBLE
                    holder.binding.left.visibility = View.GONE
                } else {
                    holder.binding.right.visibility = View.GONE
                    holder.binding.left.visibility = View.VISIBLE
                }
            }

        }
        holder.binding.root.setOnLongClickListener {
            onLongClickCallack(message)
            return@setOnLongClickListener true
        }
    }

}