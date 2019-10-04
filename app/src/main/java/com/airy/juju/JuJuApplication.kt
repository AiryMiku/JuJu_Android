package com.airy.juju

import android.app.Application
import android.content.Context
import com.airy.juju.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import kotlin.properties.Delegates


/**v
 * Created by Airy on 2019/3/28
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class JuJuApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

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