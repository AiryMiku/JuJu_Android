package com.airy.juju.ui.activity

import androidx.databinding.DataBindingUtil
import com.airy.juju.R
import com.airy.juju.base.BaseActivity
import com.airy.juju.bean.Message
import com.airy.juju.databinding.ActivityChatBinding
import com.airy.juju.ui.adapter.listView.MessagesAdapter
import com.airy.juju.util.UserCenter

class ChatActivity : BaseActivity() {

    private lateinit var binding: ActivityChatBinding
    private lateinit var adapter: MessagesAdapter

    override fun toSetContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat)
    }

    override fun initViews() {
        super.initViews()
        initToolBar(true, true, true)
        setToolBarTitle("正在与Airy消息中")
        adapter = MessagesAdapter { }
        binding.list.adapter = adapter
        val messages = ArrayList<Message>()
        messages.add(Message(0, 1,0, 0, "你好"))
        messages.add(Message(1, 1,0, UserCenter.getUserId(), "你也好"))
        messages.add(Message(2, 1,0, 0, "吃了吗？"))
        messages.add(Message(3, 1,0, UserCenter.getUserId(), "还没呢"))
        messages.add(Message(4, 1,0, 0, "最近有新的活动嘢"))
        messages.add(Message(5, 1,0, UserCenter.getUserId(), "我看看"))
        messages.add(Message(6, 1,0, UserCenter.getUserId(), "是你举办的？好棒棒哦😯"))
        messages.add(Message(6, 1,0, 0, "那可不？我可是顶尖Android Developer"))
        adapter.submitList(messages)
        binding.send.setOnClickListener {
            messages.add(Message(6, 1,0, UserCenter.getUserId(), binding.msg.text.toString()))
            adapter.notifyItemInserted(messages.size - 1)
            binding.list.smoothScrollToPosition(messages.size - 1)
        }
    }

}
