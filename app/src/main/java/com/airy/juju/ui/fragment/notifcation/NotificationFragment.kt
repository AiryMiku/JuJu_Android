package com.airy.juju.ui.fragment.notifcation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airy.juju.base.BaseFragment
import com.airy.juju.bean.Notification
import com.airy.juju.databinding.FragmentNotificationBinding
import com.airy.juju.ui.adapter.listView.NotificationsAdapter


/**
 * Created by Airy on 2019/3/15
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class NotificationFragment: BaseFragment() {

    private lateinit var binding: FragmentNotificationBinding
    private lateinit var adapter: NotificationsAdapter

    companion object {
        fun newInstance() = NotificationFragment()
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNotificationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun initPrepare() {
        adapter = NotificationsAdapter {  }
        binding.list.adapter = adapter
        val notifs = ArrayList<Notification>()
        for (v in 1..10) {
            notifs.add(Notification(v, "Airy关注你了", "2019-4-1 12:12:$v"))
            notifs.add(Notification(v, "TangPeiHua关注你了", "2019-4-1 12:12:$v"))
            notifs.add(Notification(v, "Airy取消关注你了", "2019-4-1 12:12:$v"))
            notifs.add(Notification(v, "TangPeiHua取消关注你了", "2019-4-1 12:12:$v"))
            notifs.add(Notification(v, "Android群组发布了活动", "2019-4-1 12:12:$v"))
            notifs.add(Notification(v, "Android群组有新的公告", "2019-4-1 12:12:$v"))
        }
        adapter.submitList(notifs)
    }

    override fun onInvisible() {
    }

    override fun initData() {
    }
}