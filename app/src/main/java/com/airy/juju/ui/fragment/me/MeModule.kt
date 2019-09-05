package com.airy.juju.ui.fragment.me

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
abstract class MeModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeMeFragment(): MeFragment
}