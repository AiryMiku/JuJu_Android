package com.airy.juju.ui.adapter.listView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.airy.juju.bean.Activity
import com.airy.juju.databinding.ListItemActivityBinding


/**
 * Created by Airy on 2019/3/24
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class ActivitiesAdapter(private val onClickCallback: (Activity) -> Unit)
    : ListAdapter<Activity, ActivitiesAdapter.ViewHolder>(TaskDiffCallback()) {

    class ViewHolder(val binding: ListItemActivityBinding) : RecyclerView.ViewHolder(binding.root)

    class TaskDiffCallback: DiffUtil.ItemCallback<Activity>() {
        override fun areItemsTheSame(oldItem: Activity, newItem: Activity): Boolean {
            return  oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Activity, newItem: Activity): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemActivityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val activity = getItem(position)
        holder.binding.activity = activity
        holder.binding.root.setOnClickListener {
            onClickCallback(activity)
        }
    }
}