package com.airy.juju.ui.activity

import android.view.WindowManager
import com.airy.juju.R
import com.airy.juju.base.BaseActivity
import kotlinx.android.synthetic.main.activity_welcome.*


/**
 * Created by Airy on 2019/3/11
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class WelcomeActivity : BaseActivity() {

    override fun toSetContentView() {
        setContentView(R.layout.activity_welcome)
    }

    override fun initViews() {
        super.initViews()
        btn_wel_1.setOnClickListener {
            activityIntentTo(MainActivity::class.java)
        }

        btn_wel_2.setOnClickListener {
            activityIntentTo(LoginActivity::class.java)
        }

        btn_wel_3.setOnClickListener {
            activityIntentTo(SignUpActivity::class.java)
        }

        btn_wel_4.setOnClickListener {
            activityIntentTo(GroupDetailActivity::class.java)
        }

        btn_wel_5.setOnClickListener {
            activityIntentTo(ActivityDetailActivity::class.java)
        }

        btn_wel_6.setOnClickListener {
            activityIntentTo(ChatActivity::class.java)
        }
    }
}