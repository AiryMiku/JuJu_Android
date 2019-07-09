package com.airy.juju

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates


/**
 * Created by Airy on 2019/3/28
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class JuJuApplication: Application() {

    companion object {

        var instance: JuJuApplication by Delegates.notNull()

        fun getApplicationContext(): Context = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        val crashHandler = CrashHandler.getInstance()
        crashHandler.init(this)
    }
}