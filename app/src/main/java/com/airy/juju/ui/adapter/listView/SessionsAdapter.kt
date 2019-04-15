package com.airy.juju.ui.adapter.listView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.airy.juju.bean.Session
import com.airy.juju.databinding.ListItemSessionBinding


/**
 * Created by Airy on 2019/4/15
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class SessionsAdapter(private val onClickCallback: (Session) -> Unit): ListAdapter<Session, SessionsAdapter.ViewHolder>(TaskDiffCallback()) {

    class ViewHolder(val binding: ListItemSessionBinding) : RecyclerView.ViewHolder(binding.root)

    class TaskDiffCallback: DiffUtil.ItemCallback<Session>() {
        override fun areItemsTheSame(oldItem: Session, newItem: Session): Boolean {
            return  oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Session, newItem: Session): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemSessionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val session = getItem(position)
        holder.binding.session = session
        holder.binding.root.setOnClickListener {
            onClickCallback(session)
        }
    }
}