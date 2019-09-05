package com.airy.juju.ui.fragment.playground

import androidx.lifecycle.ViewModel
import com.airy.juju.di.FragmentScoped
import com.airy.juju.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


/**
 * Created by Airy on 2019-09-03
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

@Module
@Suppress("UNUSED")
internal abstract class PlayGroundModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributePlayGroundFragment(): PlayGroundFragment

    @Binds
    @IntoMap
    @ViewModelKey(PlayGroundViewModel::class)
    internal abstract fun bindPlayGroundModel(viewModel: PlayGroundViewModel): ViewModel
}