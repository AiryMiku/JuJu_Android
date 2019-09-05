package com.airy.juju.di

import com.airy.juju.ui.activity.*
import com.airy.juju.ui.fragment.chat.ChatModule
import com.airy.juju.ui.fragment.group.GroupModule
import com.airy.juju.ui.fragment.me.MeModule
import com.airy.juju.ui.fragment.notifcation.NotificationModule
import com.airy.juju.ui.fragment.playground.PlayGroundModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by Airy on 2019-09-03
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

@Module
@Suppress("UNUSED")
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            // activity
            MainActivityModule::class,
            // fragment
            PlayGroundModule::class,
            GroupModule::class,
            NotificationModule::class,
            ChatModule::class,
            MeModule::class
        ]
    )
    internal abstract fun mainActivity(): MainActivity

    @ActivityScoped
    @ContributesAndroidInjector
    internal abstract fun welcomeActivity(): WelcomeActivity

    @ActivityScoped
    @ContributesAndroidInjector
    internal abstract fun loginActivity(): LoginActivity

    @ActivityScoped
    @ContributesAndroidInjector
    internal abstract fun chatActivity(): ChatActivity

}