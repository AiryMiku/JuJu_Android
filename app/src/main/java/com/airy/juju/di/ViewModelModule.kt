package com.airy.juju.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module


/**
 * Created by Airy on 2019-09-03
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */


@Module
@Suppress("unused")
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: JuJuViewModelFactory): ViewModelProvider.Factory
}