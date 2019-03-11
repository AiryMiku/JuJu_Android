package com.airy.juju.api

import retrofit2.http.POST


/**
 * Created by Airy on 2019/3/11
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

interface JuJuApi {

    @POST
    fun createGroup()

}