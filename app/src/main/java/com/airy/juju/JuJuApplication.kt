package com.airy.juju

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context


/**
 * Created by Airy on 2019/3/28
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class JuJuApplication: Application() {

    companion object {

        @SuppressLint("StaticFieldLeak")
        lateinit var contextSingle: Context

        fun getApplicationContext(): Context = contextSingle
    }

    override fun onCreate() {
        super.onCreate()
        contextSingle = applicationContext
    }
}