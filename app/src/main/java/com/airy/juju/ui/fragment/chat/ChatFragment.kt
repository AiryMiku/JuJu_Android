package com.airy.juju.ui.fragment.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.airy.juju.base.BaseFragment
import com.airy.juju.bean.Session
import com.airy.juju.databinding.FragmentChatBinding
import com.airy.juju.ui.adapter.listView.SessionsAdapter


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
    private lateinit var adapter: SessionsAdapter
    private lateinit var viewModel: ChatViewModel

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentChatBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun initPrepare() {
        viewModel = ViewModelProviders.of(this).get(ChatViewModel::class.java)
        adapter = SessionsAdapter {  }
        binding.list.adapter = adapter

        fakeData()
//        viewModel.getSessions()
        subscribeUI()
    }

    override fun onInvisible() {
    }

    override fun initData() {
    }

    private fun subscribeUI() {
        viewModel.sessions.observe(this , Observer {
            if (it.code == 0) {
                adapter.submitList(it.data.list)
            }
        })
    }

    private fun fakeData() {
        val sessions = ArrayList<Session>()
        sessions.add(Session(0,0,1,1,"hello","Airy","16:11:51"))
        sessions.add(Session(0,0,1,1,"吃了吗？","TangPeiHua","15:11:12"))
        sessions.add(Session(0,0,1,1,"有新的活动嘢","Jack","13:11:41"))
        sessions.add(Session(0,0,1,1,"来看看这个","Camel","15:13:31"))
        sessions.add(Session(0,0,1,1,"今夜月色真美","Emily","16:13:11"))
        sessions.add(Session(0,0,1,1,"wow，a wondeful night","Daisy","12:31:11"))
        adapter.submitList(sessions)
    }
}