package com.airy.juju.ui.adapter.listView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.airy.juju.bean.User
import com.airy.juju.databinding.ListItemUserBinding


/**
 * Created by Airy on 2019/3/25
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class UsersAdapter(private val onClickCallback: (User) -> Unit): ListAdapter<User, UsersAdapter.ViewHolder>(TaskDiffCallback()) {

    class TaskDiffCallback: DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return  oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        holder.binding.user = user
        holder.binding.root.setOnClickListener {
            onClickCallback(user)
        }
    }

    class ViewHolder(val binding: ListItemUserBinding) : RecyclerView.ViewHolder(binding.root)


}