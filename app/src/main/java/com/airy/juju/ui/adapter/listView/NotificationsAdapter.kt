package com.airy.juju.ui.adapter.listView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.airy.juju.bean.Notification
import com.airy.juju.databinding.ListItemNotificationBinding


/**
 * Created by Airy on 2019/3/28
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class NotificationsAdapter(private val onClickCallback: (Notification) -> Unit):
    ListAdapter<Notification, NotificationsAdapter.ViewHolder>(NotificationsAdapter.TaskDiffCallback()) {

    class TaskDiffCallback: DiffUtil.ItemCallback<Notification>() {
        override fun areItemsTheSame(oldItem: Notification, newItem: Notification): Boolean {
            return  oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Notification, newItem: Notification): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notification = getItem(position)
        holder.binding.notifiaction = notification
        holder.binding.root.setOnClickListener {
            onClickCallback(notification)
        }
    }

    class ViewHolder(val binding: ListItemNotificationBinding) : RecyclerView.ViewHolder(binding.root)
}