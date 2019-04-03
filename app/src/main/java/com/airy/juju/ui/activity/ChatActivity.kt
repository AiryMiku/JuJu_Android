package com.airy.juju.ui.activity

import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.airy.juju.R
import com.airy.juju.base.BaseActivity
import com.airy.juju.databinding.ActivityChatBinding

class ChatActivity : BaseActivity() {

    private lateinit var binding: ActivityChatBinding

    override fun toSetContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat)
    }

    override fun initViews() {
        super.initViews()
        initToolBar(true, true, true)
    }

}
