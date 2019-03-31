package com.airy.juju.ui.fragment.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airy.juju.base.BaseFragment
import com.airy.juju.databinding.FragmentChatBinding


/**
 * Created by Airy on 2019/3/15
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class ChatFragment :BaseFragment() {

    companion object {
        fun newInstance() = ChatFragment()
    }

    private lateinit var binding: FragmentChatBinding

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentChatBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun initPrepare() {
    }

    override fun onInvisible() {
    }

    override fun initData() {
    }
}