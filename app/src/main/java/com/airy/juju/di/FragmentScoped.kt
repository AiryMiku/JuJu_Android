package com.airy.juju.di

import javax.inject.Scope


/**
 * Created by Airy on 2019-09-03
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

@Scope
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class FragmentScoped