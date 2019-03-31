package com.airy.juju.ui.fragment.notifcation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airy.juju.base.BaseFragment
import com.airy.juju.databinding.FragmentNotificationBinding


/**
 * Created by Airy on 2019/3/15
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class NotificationFragment: BaseFragment() {

    private lateinit var binding: FragmentNotificationBinding

    companion object {
        fun newInstance() = NotificationFragment()
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNotificationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun initPrepare() {
    }

    override fun onInvisible() {
    }

    override fun initData() {
    }
}