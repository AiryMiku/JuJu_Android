package com.airy.juju.ui.fragment.notifcation

import com.airy.juju.di.FragmentScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by Airy on 2019-09-05
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

@Module
@Suppress("unused")
abstract class NotificationModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeNotificationFragment(): NotificationFragment
}