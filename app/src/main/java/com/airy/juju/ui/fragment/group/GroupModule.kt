package com.airy.juju.ui.fragment.group

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
abstract class GroupModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeGroupFragment(): GroupFragment
}