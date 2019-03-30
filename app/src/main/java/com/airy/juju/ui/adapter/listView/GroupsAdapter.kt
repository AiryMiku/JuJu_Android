package com.airy.juju.ui.adapter.listView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.airy.juju.bean.Group
import com.airy.juju.databinding.ListItemGroupBinding

/**
 * Created by Airy on 2019/3/13
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class GroupsAdapter(private val onClickCallback: (Group) -> Unit) : ListAdapter<Group, GroupsAdapter.ViewHolder>(
    TaskDiffCallback()
) {

    class ViewHolder(val binding: ListItemGroupBinding) : RecyclerView.ViewHolder(binding.root)

    class TaskDiffCallback: DiffUtil.ItemCallback<Group>() {
        override fun areItemsTheSame(oldItem: Group, newItem: Group): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Group, newItem: Group): Boolean {
            return oldItem == newItem
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val group = getItem(position)
        holder.binding.group = group        // bind your data
        holder.binding.root.setOnClickListener { // bind listener
            onClickCallback(group)
        }
    }

}


