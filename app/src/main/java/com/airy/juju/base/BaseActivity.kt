package com.airy.juju.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.airy.juju.R
import com.google.android.material.snackbar.Snackbar


/**
 * Created by Airy on 2019/3/11
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

abstract class BaseActivity : AppCompatActivity() {

    val TAG = this.javaClass.name
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        toSetContentView()
        loadData()
        initViews()
    }

    open fun initViews() {

    }

    open fun loadData(){}

    abstract fun toSetContentView()

    fun makeToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun activityIntentTo(clz: Class<*>) {
        val intent = Intent(this, clz)
        startActivity(intent)
    }

    fun initToolBar(homeButton: Boolean, backArrow: Boolean, showTitle: Boolean) {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(homeButton)
            actionBar.setDisplayHomeAsUpEnabled(backArrow)
            actionBar.setDisplayShowTitleEnabled(showTitle)
        }
    }

    fun setToolBarTitle(title: String) {
        toolbar.title = title
    }

    fun makeSnackar(layout: View,message: String) {
        Snackbar.make(layout, message, Snackbar.LENGTH_SHORT).show()
    }



}