package com.airy.juju.ui.activity

import android.view.View
import android.view.WindowManager
import com.airy.juju.R
import com.airy.juju.base.BaseActivity
import kotlinx.android.synthetic.main.activity_welcome_dev.*
import android.view.animation.Animation
import android.content.Intent
import android.view.animation.AlphaAnimation
import com.airy.juju.util.UserCenter


/**
 * Created by Airy on 2019/3/11
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class WelcomeActivity : BaseActivity() {

    private lateinit var mContentView: View

    override fun toSetContentView() {
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        mContentView = View.inflate(this, R.layout.activity_welcome, null)
        setContentView(mContentView)
    }

    override fun initViews() {
        super.initViews()
        animationLogic()
    }

    private fun animationLogic() {
        val animation = AlphaAnimation(0.3f, 1.0f)
        animation.duration = 900
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {
                if (UserCenter.isLogin(this@WelcomeActivity)) {
                    val intent = Intent(this@WelcomeActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val intent = Intent(this@WelcomeActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            override fun onAnimationRepeat(animation: Animation) {}
        })
        mContentView.startAnimation(animation)
    }

//    fun bindButton() {
//        btn_wel_1.setOnClickListener {
//            activityIntentTo(MainActivity::class.java)
//        }
//
//        btn_wel_2.setOnClickListener {
//            activityIntentTo(LoginActivity::class.java)
//        }
//
//        btn_wel_3.setOnClickListener {
//            activityIntentTo(SignUpActivity::class.java)
//        }
//
//        btn_wel_4.setOnClickListener {
//            activityIntentTo(GroupDetailActivity::class.java)
//        }
//
//        btn_wel_5.setOnClickListener {
//            activityIntentTo(ActivityDetailActivity::class.java)
//        }
//
//        btn_wel_6.setOnClickListener {
//            activityIntentTo(ChatActivity::class.java)
//        }
//    }
}