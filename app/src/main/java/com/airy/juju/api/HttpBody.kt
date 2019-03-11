package com.airy.juju.api


/**
 * Created by Airy on 2019/3/11
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

data class HttpBody<T>(val Code: Int, val data: T)