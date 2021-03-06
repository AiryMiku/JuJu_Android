package com.airy.juju.ui.activity

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.airy.juju.Common
import com.airy.juju.R
import com.airy.juju.base.BaseActivity
import com.airy.juju.bean.Message
import com.airy.juju.databinding.ActivityChatBinding
import com.airy.juju.ui.adapter.listView.MessagesAdapter
import com.airy.juju.util.UserCenter
import com.airy.juju.viewModel.activity.ChatViewModel

class ChatActivity : BaseActivity() {

    private lateinit var binding: ActivityChatBinding
    private lateinit var adapter: MessagesAdapter
    private lateinit var viewModel: ChatViewModel
    private var sessionId = -1

    override fun toSetContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat)
    }

    override fun initViews() {
        super.initViews()
        viewModel = ViewModelProviders.of(this).get(ChatViewModel::class.java)
        initToolBar(true, true, true)
        adapter = MessagesAdapter { }
        binding.list.adapter = adapter



        binding.refresh.setOnRefreshListener {
            refresh()
            binding.refresh.isRefreshing = true
        }

        binding.send.setOnClickListener {
            if (binding.msg.text.isNotBlank()) {
                val params = HashMap<String, Any>()
                params["access_token"] = UserCenter.getUserToken()
                params["session_id"] = sessionId
                params["content"] = binding.msg.text.toString()
                viewModel.postMessage(params)
            } else {
                makeToast("不可发送空消息哦😯")
            }
        }
//        fakeData()
        initSession()
        subsrcibeUI()
    }

    private fun subsrcibeUI() {
        viewModel.session.observe(this, Observer {
            if (it.code == 0) {
                sessionId = it.data.id
                setToolBarTitle("正在与${it.data.title}消息中")
                refresh()
//                makeToast("message btn, id: $sessionId")
            }
        })

        viewModel.messages.observe(this, Observer {
            if (it.code == 0) {
                adapter.submitList(it.data.list)
                adapter.notifyItemInserted(it.data.count)
                binding.list.smoothScrollToPosition(it.data.count)
            } else {
                makeToast("拉取消息失败")
            }
            binding.refresh.isRefreshing = false
        })

        viewModel.postMessageResult.observe(this, Observer {
            if (it==true) {
                makeToast("发送成功")
                binding.msg.text.clear()
                refresh()
            }
        })
    }

    private fun fakeData() {
        setToolBarTitle("正在与Airy消息中")
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
            if (binding.msg.text.isNotBlank()) {
                messages.add(Message(6, 1,0, UserCenter.getUserId(), binding.msg.text.toString()))
                adapter.notifyItemInserted(messages.size - 1)
                binding.list.smoothScrollToPosition(messages.size - 1)
                binding.msg.text.clear()
            } else {
                makeToast("不可发送空消息哦😯")
            }
        }
    }

    private fun initSession() {
        val type = intent.getStringExtra(Common.ChatEnterType.KEY)
        when(type) {
            Common.ChatEnterType.FROM_MESSAGE_BUTTON -> {
                val params = HashMap<String, Any>()
                params["access_token"] = UserCenter.getUserToken()
                params["type"] = 1 // 个人
                params["left_id"] = UserCenter.getUserId() // sender
                params["right_id"] = intent.getIntExtra(Common.ParamTranferKey.USER_ID_KEY, 0) //reciver
                viewModel.getSession(params)
            }
            Common.ChatEnterType.FROM_SESSION_LIST -> {
                sessionId = intent.getIntExtra(Common.ParamTranferKey.SESSION_ID_KEY, 0)
                val params = HashMap<String, Any>()
                params["access_token"] = UserCenter.getUserToken()
                params["session_id"] = sessionId
                viewModel.getSessionById(params)
            }
            Common.ChatEnterType.FROM_GROUP -> {
                val params = HashMap<String, Any>()
                val gid = intent.getIntExtra(Common.ParamTranferKey.GROUP_ID_KEY, 0)
                params["access_token"] = UserCenter.getUserToken()
                params["type"] = 0 // 群组
                params["left_id"] = gid // group_id
                params["right_id"] = 0
                viewModel.getSession(params)
            }
        }
    }

    private fun refresh() {
        val params = HashMap<String, Any>()
        params["access_token"] = UserCenter.getUserToken()
        params["session_id"] = sessionId
        params["page"] = 1
        params["size"] = 1000
        viewModel.getMessages(params)
    }


}
