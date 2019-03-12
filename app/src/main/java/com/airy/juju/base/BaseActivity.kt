package com.airy.juju.base

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import android.widget.Toast


/**
 * Created by Airy on 2019/3/11
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

abstract class BaseActivity : AppCompatActivity() {

    public final val TAG = this.javaClass.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setContentViewId())
        loadData()
        initViews()
    }

    open fun initViews() {

    }

    open fun loadData(){}

    abstract fun setContentViewId(): Int

    fun makeToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun activityIntentTo(clz: Class<*>) {
        val intent = Intent(this, clz)
        startActivity(intent)
    }



}