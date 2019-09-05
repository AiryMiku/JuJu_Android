package com.airy.juju.di

import com.airy.juju.JuJuApplication
import com.airy.juju.ui.fragment.playground.PlayGroundModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


/**
 * Created by Airy on 2019-09-03
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 *
 * The bridge between dependings to component
 */
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBindingModule::class,
        ViewModelModule::class,
        AppModule::class
    ]
)
interface AppComponent: AndroidInjector<JuJuApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: JuJuApplication): AppComponent
    }
}