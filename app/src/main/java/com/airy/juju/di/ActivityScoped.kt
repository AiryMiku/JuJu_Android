package com.airy.juju.di

import javax.inject.Scope


/**
 * Created by Airy on 2019-09-03
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 *
 * use custom scoped make instance reused depending on the component’s lifecycle
 */

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScoped