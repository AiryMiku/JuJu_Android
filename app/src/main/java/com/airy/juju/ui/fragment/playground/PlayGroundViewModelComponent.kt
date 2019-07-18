package com.airy.juju.ui.fragment.playground

import dagger.Component
import javax.inject.Singleton


/**
 * Created by Airy on 2019-07-18
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

@Singleton
@Component
interface PlayGroundViewModelComponent {
    fun inject(viewModel: PlayGroundViewModel)
}